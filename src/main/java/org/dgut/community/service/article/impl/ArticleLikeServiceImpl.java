package org.dgut.community.service.article.impl;

import org.dgut.community.NotFoundException;
import org.dgut.community.entity.ArticleCollect;
import org.dgut.community.entity.ArticleLike;
import org.dgut.community.entity.FourmArticle;
import org.dgut.community.repository.article.FourmRepository;
import org.dgut.community.repository.article.LikeRepository;
import org.dgut.community.resultenum.Result;
import org.dgut.community.resultenum.ResultEnum;
import org.dgut.community.service.article.IArticleLike;
import org.dgut.community.util.ResultUtil;
import org.dgut.community.util.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ArticleLikeServiceImpl implements IArticleLike {

    private LikeRepository likeRepository;
    private FourmRepository fourmRepository;

    public ArticleLikeServiceImpl(LikeRepository likeRepository, FourmRepository fourmRepository) {
        this.likeRepository = likeRepository;
        this.fourmRepository = fourmRepository;
    }

    @Override
    public List<FourmArticle> findByUserId(Long id, Pageable pageable) {
        Page<ArticleLike> articleLikes = likeRepository.findByUserId(id, pageable);
        List<Long> userId = new ArrayList<Long>();
        for (ArticleLike articleLike : articleLikes){
            userId.add(articleLike.getArticle().getArticleId());
        }
        Iterable<Long> longs = new Iterable<Long>() {
            @Override
            public Iterator<Long> iterator() {
                return userId.iterator();
            }
        };
        List<FourmArticle> fourmArticles = fourmRepository.findAllById(longs);
        for (FourmArticle fourmArticle : fourmArticles){
            Util.setArticlePhotos(fourmArticle);
            fourmArticle.getUser().setUserPassword(null);
        }
        return fourmArticles;
    }

    @Override
    public ResponseEntity<Result> save(Long articleId, ArticleLike like) {
        if (likeRepository.findByArticle_ArticleIdAndUserId(articleId, like.getUserId()) != null){
            throw new NotFoundException(ResultEnum.NOT_REPEAT);
        }
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
            ArticleLike articleLike = likeRepository.findByArticle_ArticleIdAndUserId(articleId, like.getUserId());
            if (articleLike == null){
                throw new NotFoundException(ResultEnum.NOT_REPEAT);
            }
            fourmArticle.setArticleLike(fourmArticle.getArticleLike() - 1);
            fourmRepository.save(fourmArticle);
            articleLike.setArticle(null);
            likeRepository.delete(articleLike);
            return ResponseEntity.ok(ResultUtil.success());
        }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }
}
