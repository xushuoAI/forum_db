package org.dgut.community.service.news.impl;

import org.dgut.community.NotFoundException;
import org.dgut.community.entity.NewsReply;
import org.dgut.community.repository.news.NewsCommentRepository;
import org.dgut.community.repository.news.NewsReplyRepository;
import org.dgut.community.resultenum.ResultEnum;
import org.dgut.community.service.news.INewsReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NewsReplyServiceImpl implements INewsReply {
    private NewsReplyRepository newsReplyRepository;
    private NewsCommentRepository commentRepository;

    public NewsReplyServiceImpl(NewsReplyRepository newsReplyRepository, NewsCommentRepository commentRepository) {
        this.newsReplyRepository = newsReplyRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Page<NewsReply> findByCommentId(Long commentId, Pageable pageable) {
        return newsReplyRepository.findByComment_newsCommentId(commentId, pageable);
    }

    @Override
    public NewsReply findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        return newsReplyRepository.findById(id).map(newsReply -> {
            return commentRepository.findById(newsReply.getComment().getNewsCommentId()).map(newsComment -> {
                newsComment.setNewsCommentReply(newsComment.getNewsCommentReply() - 1);
                commentRepository.save(newsComment);
                newsReply.setComment(null);
                newsReplyRepository.delete(newsReply);
                return ResponseEntity.ok().build();
            }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
        }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public NewsReply updateById(Long id) {
        return null;
    }

    @Override
    public NewsReply save(Long id, NewsReply newsReply) {
        return commentRepository.findById(id).map(newsComment -> {
            newsComment.setNewsCommentReply(newsComment.getNewsCommentReply() + 1);
            commentRepository.save(newsComment);
            newsReply.setComment(newsComment);
            return newsReplyRepository.save(newsReply);
        }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }
}
