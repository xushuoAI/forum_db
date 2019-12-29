package org.dgut.community.repository.article;

import org.dgut.community.entity.ArticleLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<ArticleLike, Long> {
    Page<ArticleLike> findByUserId(Long userId, Pageable pageable);
    ArticleLike findByArticle_ArticleIdAndUserId(Long articleId, Long userId);
//    ArticleLike findByArticle_ArticleId(Long articleId);
}
