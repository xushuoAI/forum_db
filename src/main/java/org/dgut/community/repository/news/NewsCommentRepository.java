package org.dgut.community.repository.news;

import org.dgut.community.entity.NewsComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsCommentRepository extends JpaRepository<NewsComment, Long> {
    Page<NewsComment> findByNews_newsId(Long newsId, Pageable pageable);
}
