package org.dgut.community.repository.article;

import org.dgut.community.entity.ArticleCollect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectRepository extends JpaRepository<ArticleCollect, Long> {
}
