package org.dgut.community.repository.article;

import org.dgut.community.entity.ArticleReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<ArticleReply, Long> {
}
