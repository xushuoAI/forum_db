package org.dgut.community.service.article;

import org.dgut.community.entity.ArticleComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IComment {

    Page<ArticleComment> findByArticleId(Long articleId, Pageable pageable);

    ArticleComment updateLike(Long id, int num);

    ArticleComment updateTop(Long id, int num);

    String deleteById(Long id);

    ArticleComment save(Long articleId, ArticleComment articleComment);
}