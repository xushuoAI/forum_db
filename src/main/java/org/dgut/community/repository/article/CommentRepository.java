package org.dgut.community.repository.article;

import org.dgut.community.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<ArticleComment, Long> {
}
