package org.dgut.community.service.article.impl;

import org.dgut.community.NotFoundException;
import org.dgut.community.entity.ArticleLike;
import org.dgut.community.repository.article.FourmRepository;
import org.dgut.community.repository.article.LikeRepository;
import org.dgut.community.resultenum.Result;
import org.dgut.community.resultenum.ResultEnum;
import org.dgut.community.service.article.IArticleLike;
import org.dgut.community.util.ResultUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ArticleLikeServiceImpl implements IArticleLike {

    private LikeRepository likeRepository;
    private FourmRepository fourmRepository;

    public ArticleLikeServiceImpl(LikeRepository likeRepository, FourmRepository fourmRepository) {
        this.likeRepository = likeRepository;
        this.fourmRepository = fourmRepository;
    }

    @Override
    public ResponseEntity<Result> save(Long articleId, ArticleLike like) {
        return fourmRepository.findById(articleId).map(fourmArticle -> {
            fourmArticle.setArticleLike(fourmArticle.getArticleLike() + 1);
            fourmRepository.save(fourmArticle);
            like.setArticle(fourmArticle);
            likeRepository.save(like);
            return ResponseEntity.ok(ResultUtil.success());
        }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public ResponseEntity<?> deleteByArticleId(Long articleId, ArticleLike like) {
        return fourmRepository.findById(articleId).map(fourmArticle -> {
            fourmArticle.setArticleLike(fourmArticle.getArticleLike() - 1);
            fourmRepository.save(fourmArticle);
            ArticleLike articleLike = likeRepository.findByArticle_ArticleIdAndUserId(articleId, like.getUserId());
            articleLike.setArticle(null);
            likeRepository.delete(articleLike);
            return ResponseEntity.ok(ResultUtil.success());
        }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }
}
