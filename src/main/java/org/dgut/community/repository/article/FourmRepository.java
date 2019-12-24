package org.dgut.community.repository.article;

import org.dgut.community.entity.FourmArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FourmRepository extends JpaRepository<FourmArticle, Long> {
    Page<FourmArticle> findByArticleContentLike(String articleContent, Pageable pageable);
    Page<FourmArticle> findAll(Pageable pageable);
    Page<FourmArticle> findByUser_userId(Long id, Pageable pageable);
}
