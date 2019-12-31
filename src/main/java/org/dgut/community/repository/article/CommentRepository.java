package org.dgut.community.repository.article;

import org.dgut.community.entity.ArticleComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<ArticleComment, Long> {
    Page<ArticleComment> findByArticle_articleId(Long articleId, Pageable pageable);
    Page<ArticleComment> findByArticle_User_UserId(Long userId, Pageable pageable);
}
