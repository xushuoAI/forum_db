package org.dgut.community.service.article.impl;

import org.dgut.community.NotFoundException;
import org.dgut.community.entity.ArticleComment;
import org.dgut.community.repository.article.CommentRepository;
import org.dgut.community.repository.article.FourmRepository;
import org.dgut.community.repository.user.UserRepository;
import org.dgut.community.service.article.IComment;
import org.dgut.community.util.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CommentServiceImpl implements IComment {
    private CommentRepository commentRepository;
    private FourmRepository fourmRepository;

    public CommentServiceImpl(CommentRepository commentRepository, FourmRepository fourmRepository) {
        this.commentRepository = commentRepository;
        this.fourmRepository = fourmRepository;
    }

    @Override
    public Page<ArticleComment> findByArticleId(Long articleId, Pageable pageable) {
        return commentRepository.findByArticle_articleId(articleId, pageable);
    }

    @Override
    public ArticleComment updateLike(Long id, int num) {
        return commentRepository.findById(id).map(articleComment -> {
            if (num == 1){
                articleComment.setCommentLike(articleComment.getCommentLike() + 1);
                return commentRepository.save(articleComment);
            }else {
                if (articleComment.getCommentLike() == 0){
                    return commentRepository.save(articleComment);
                }
                articleComment.setCommentLike(articleComment.getCommentLike() - 1);
                return commentRepository.save(articleComment);
            }
        }).orElseThrow(() -> new NotFoundException("没有该评论"));
    }

    @Override
    public ArticleComment updateTop(Long id, int num) {
        return commentRepository.findById(id).map(articleComment -> {
            if (num == 1){
                articleComment.setIsTop(1);
            }else {
                articleComment.setIsTop(0);
            }
            return commentRepository.save(articleComment);
        }).orElseThrow(() -> new NotFoundException("没有该评论"));
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        return commentRepository.findById(id).map(articleComment -> {
            return fourmRepository.findById(articleComment.getArticle().getArticleId()).map(article -> {
                article.setArticleCommentTimes(article.getArticleCommentTimes() - 1);
                fourmRepository.save(article);
                articleComment.setArticle(null);
                commentRepository.delete(articleComment);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new NotFoundException("没有该帖子"));
        }).orElseThrow(() -> new NotFoundException("没有该帖子"));
    }

    @Override
    public ArticleComment save(Long articleId, ArticleComment articleComment) {
        return fourmRepository.findById(articleId).map(article -> {
            article.setArticleCommentTimes(article.getArticleCommentTimes() + 1);
            articleComment.setArticle(article);
            articleComment.setCommentCreateTime(LocalDate.parse(Util.getTime()));
            fourmRepository.save(article);
            return commentRepository.save(articleComment);
        }).orElseThrow(() -> new NotFoundException("没有该帖子"));
    }
}
