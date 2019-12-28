package org.dgut.community.service.article;

import org.dgut.community.entity.ArticleComment;
import org.dgut.community.resultenum.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IComment {

    Page<ArticleComment> findByArticleId(Long articleId, Pageable pageable);

    ResponseEntity<Result> updateLike(Long id, int num);

    ResponseEntity<Result> updateTop(Long id, int num);

    ResponseEntity<?> deleteById(Long id);

    ResponseEntity<Result> save(Long articleId, ArticleComment articleComment);
}
