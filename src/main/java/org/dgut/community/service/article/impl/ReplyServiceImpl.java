package org.dgut.community.service.article.impl;

import org.dgut.community.entity.ArticleReply;
import org.dgut.community.repository.article.CommentRepository;
import org.dgut.community.repository.article.FourmRepository;
import org.dgut.community.repository.article.ReplyRepository;
import org.dgut.community.service.article.IReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements IReply {
    private ReplyRepository replyRepository;
    private FourmRepository fourmRepository;
    private CommentRepository commentRepository;

    public ReplyServiceImpl(ReplyRepository replyRepository, FourmRepository fourmRepository, CommentRepository commentRepository) {
        this.replyRepository = replyRepository;
        this.fourmRepository = fourmRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Page<ArticleReply> findByCommentId(Long commentId, Pageable pageable) {
        return replyRepository.findByComment_commentId(commentId, pageable);
    }

    @Override
    public ArticleReply findById(Long id) {
        return null;
    }

    @Override
    public String deleteById(Long id) {
        return replyRepository.findById(id).map(articleReply -> {
            return commentRepository.findById(articleReply.getComment().getCommentId()).map(articleComment -> {
                fourmRepository.findById(articleComment.getArticle().getArticleId()).map(fourmArticle -> {
                    fourmArticle.setArticleCommentTimes(fourmArticle.getArticleCommentTimes() - 1);
                    articleComment.setCommentReply(articleComment.getCommentReply() - 1);
                    fourmRepository.save(fourmArticle);
                    commentRepository.save(articleComment);
                    return replyRepository.save(articleReply);
                }).orElseThrow(() -> new RuntimeException("没有该帖子"));
                articleReply.setComment(null);
                replyRepository.delete(articleReply);
                return "删除成功";
            }).orElseThrow(() -> new RuntimeException("没有该评论"));
        }).orElseThrow(() -> new RuntimeException("没有该评论"));
    }

    @Override
    public ArticleReply save(Long id, ArticleReply articleReply) {
        return commentRepository.findById(id).map(articleComment -> {
            return fourmRepository.findById(articleComment.getArticle().getArticleId()).map(fourmArticle -> {
                fourmArticle.setArticleCommentTimes(fourmArticle.getArticleCommentTimes() + 1);
                articleComment.setCommentReply(articleComment.getCommentReply() + 1);
                fourmRepository.save(fourmArticle);
                commentRepository.save(articleComment);
                replyRepository.save(articleReply);
                articleReply.setComment(articleComment);
                return replyRepository.save(articleReply);
            }).orElseThrow(() -> new RuntimeException("没有该帖子"));
        }).orElseThrow(() -> new RuntimeException("没有该评论"));
    }
}