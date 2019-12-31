package org.dgut.community.service.article.impl;

import org.dgut.community.NotFoundException;
import org.dgut.community.entity.ArticleComment;
import org.dgut.community.entity.User;
import org.dgut.community.repository.article.CommentRepository;
import org.dgut.community.repository.article.FourmRepository;
import org.dgut.community.repository.user.UserRepository;
import org.dgut.community.resultenum.Result;
import org.dgut.community.resultenum.ResultEnum;
import org.dgut.community.service.article.IComment;
import org.dgut.community.util.ResultUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements IComment {
    private CommentRepository commentRepository;
    private FourmRepository fourmRepository;
    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, FourmRepository fourmRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.fourmRepository = fourmRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<ArticleComment> findByArticle_User_UserId(Long userId, Pageable pageable) {
        Page<ArticleComment> comments = commentRepository.findByArticle_User_UserId(userId, pageable);
        for (ArticleComment comment : comments){
            User user = findByUserId(comment.getUserId());
            comment.setUserName(user.getUserName());
            comment.setUserHeadImg(user.getUserHeadImg());
        }
        return comments;
    }

    @Override
    public Page<ArticleComment> findByArticleId(Long articleId, Pageable pageable) {
        Page<ArticleComment> comments = commentRepository.findByArticle_articleId(articleId, pageable);
        for (ArticleComment comment : comments){
            User user = findByUserId(comment.getUserId());
            comment.setUserName(user.getUserName());
            comment.setUserHeadImg(user.getUserHeadImg());
        }
        return comments;
    }

    @Override
    public ResponseEntity<Result> updateLike(Long id, int num) {
        return commentRepository.findById(id).map(articleComment -> {
            if (num == 1){
                articleComment.setCommentLike(articleComment.getCommentLike() + 1);
                commentRepository.save(articleComment);
                return ResponseEntity.ok(ResultUtil.success());
            }else {
//                if (articleComment.getCommentLike() == 0){
//                    return commentRepository.save(articleComment);
//                }
                articleComment.setCommentLike(articleComment.getCommentLike() - 1);
                commentRepository.save(articleComment);
                return ResponseEntity.ok(ResultUtil.success());
            }
        }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public ResponseEntity<Result> updateTop(Long id, int num) {
        return commentRepository.findById(id).map(articleComment -> {
            if (num == 1){
                articleComment.setIsTop(1);
            }else {
                articleComment.setIsTop(0);
            }
            commentRepository.save(articleComment);
            return ResponseEntity.ok(ResultUtil.success());
        }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        return commentRepository.findById(id).map(articleComment -> {
            return fourmRepository.findById(articleComment.getArticle().getArticleId()).map(article -> {
                article.setArticleCommentTimes(article.getArticleCommentTimes() - 1);
                fourmRepository.save(article);
                articleComment.setArticle(null);
                commentRepository.delete(articleComment);
                return ResponseEntity.ok(ResultUtil.success());
            }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
        }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public ResponseEntity<Result> save(Long articleId, ArticleComment articleComment) {
        return fourmRepository.findById(articleId).map(article -> {
            article.setArticleCommentTimes(article.getArticleCommentTimes() + 1);
            articleComment.setArticle(article);
            fourmRepository.save(article);
            ArticleComment comment = commentRepository.save(articleComment);
            return ResponseEntity.ok(ResultUtil.success(comment));
        }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    User findByUserId(Long userId) {
        return userRepository.findById(userId).map(user -> {
            user.setUserPassword(null);
            return user;
        }).orElseThrow(()-> new NotFoundException(ResultEnum.USER_NOT_EXIST));
    }
}
