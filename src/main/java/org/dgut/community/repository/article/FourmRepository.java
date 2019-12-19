package org.dgut.community.repository.article;

import org.dgut.community.entity.FourmArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FourmRepository extends JpaRepository<FourmArticle, Long> {
}
