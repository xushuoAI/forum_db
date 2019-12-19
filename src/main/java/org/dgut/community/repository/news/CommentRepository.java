package org.dgut.community.repository.news;

import org.dgut.community.entity.NewsComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<NewsComment, Long> {
}
