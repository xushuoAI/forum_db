package org.dgut.community.service.article;

import org.dgut.community.entity.ArticleLike;
import org.springframework.http.ResponseEntity;

public interface IArticleLike {
    ResponseEntity<ArticleLike> save(Long articleId, ArticleLike like);

    ResponseEntity<?> deleteByArticleId(Long articleId, ArticleLike like);
}
