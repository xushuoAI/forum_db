package org.dgut.community.repository.news;

import org.dgut.community.entity.NewsReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsReplyRepository extends JpaRepository<NewsReply, Long> {
    Page<NewsReply> findByComment_newsCommentId(Long commentId, Pageable pageable);
}
