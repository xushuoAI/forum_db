package org.dgut.community.service.article.impl;

import org.dgut.community.NotFoundException;
import org.dgut.community.entity.ArticleReply;
import org.dgut.community.entity.User;
import org.dgut.community.repository.article.CommentRepository;
import org.dgut.community.repository.article.FourmRepository;
import org.dgut.community.repository.article.ReplyRepository;
import org.dgut.community.repository.user.UserRepository;
import org.dgut.community.resultenum.Result;
import org.dgut.community.resultenum.ResultEnum;
import org.dgut.community.service.article.IReply;
import org.dgut.community.util.ResultUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements IReply {
    private ReplyRepository replyRepository;
    private FourmRepository fourmRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;

    public ReplyServiceImpl(ReplyRepository replyRepository, FourmRepository fourmRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.replyRepository = replyRepository;
        this.fourmRepository = fourmRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<ArticleReply> findByCommentId(Long commentId, Pageable pageable) {
        Page<ArticleReply> replies = replyRepository.findByComment_commentId(commentId, pageable);
        for (ArticleReply reply : replies){
            User user = findByUserName(reply.getFromUserName());
            reply.setUserHeadImg(user.getUserHeadImg());
        }
        return replies;
    }

    @Override
    public ArticleReply findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        return replyRepository.findById(id).map(articleReply -> {
            return commentRepository.findById(articleReply.getComment().getCommentId()).map(articleComment -> {
                fourmRepository.findById(articleComment.getArticle().getArticleId()).map(fourmArticle -> {
                    fourmArticle.setArticleCommentTimes(fourmArticle.getArticleCommentTimes() - 1);
                    articleComment.setCommentReply(articleComment.getCommentReply() - 1);
                    fourmRepository.save(fourmArticle);
                    commentRepository.save(articleComment);
                    return replyRepository.save(articleReply);
                }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
                articleReply.setComment(null);
                replyRepository.delete(articleReply);
                return ResponseEntity.ok(ResultUtil.success());
            }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
        }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public ResponseEntity<Result> save(Long id, ArticleReply articleReply) {
        User user = findByUserName(articleReply.getFromUserName());
        return commentRepository.findById(id).map(articleComment -> {
            return fourmRepository.findById(articleComment.getArticle().getArticleId()).map(fourmArticle -> {
                fourmArticle.setArticleCommentTimes(fourmArticle.getArticleCommentTimes() + 1);
                articleComment.setCommentReply(articleComment.getCommentReply() + 1);
                fourmRepository.save(fourmArticle);
                commentRepository.save(articleComment);
                replyRepository.save(articleReply);
                articleReply.setComment(articleComment);
                ArticleReply reply = replyRepository.save(articleReply);
                reply.setUserHeadImg(user.getUserHeadImg());
                return ResponseEntity.ok(ResultUtil.success(reply));
            }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
        }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    User findByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
//        if (user == null){
//            throw new NotFoundException(ResultEnum.USER_NOT_EXIST);
//        }
        user.setUserPassword(null);
        return user;
    }
}
