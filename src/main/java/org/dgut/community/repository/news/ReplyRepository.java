package org.dgut.community.repository.news;

import org.dgut.community.entity.NewsReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<NewsReply, Long> {
}
