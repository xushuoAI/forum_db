package org.dgut.community.service.article;

import org.dgut.community.entity.ArticleLike;
import org.dgut.community.entity.FourmArticle;
import org.dgut.community.resultenum.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IArticleLike {

    List<FourmArticle> findByUserId(Long id, Pageable pageable);

    ResponseEntity<Result> save(Long articleId, ArticleLike like);

    ResponseEntity<?> deleteByArticleId(Long articleId, ArticleLike like);
}
