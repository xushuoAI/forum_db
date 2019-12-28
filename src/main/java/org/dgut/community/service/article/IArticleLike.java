package org.dgut.community.service.article;

import org.dgut.community.entity.ArticleLike;
import org.dgut.community.resultenum.Result;
import org.springframework.http.ResponseEntity;

public interface IArticleLike {
    ResponseEntity<Result> save(Long articleId, ArticleLike like);

    ResponseEntity<?> deleteByArticleId(Long articleId, ArticleLike like);
}
