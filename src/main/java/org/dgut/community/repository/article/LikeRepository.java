package org.dgut.community.repository.article;

import org.dgut.community.entity.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<ArticleLike, Long> {
    ArticleLike findByArticle_ArticleIdAndUserId(Long articleId, Long userId);
}
