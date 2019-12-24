package org.dgut.community.repository.article;

import org.dgut.community.entity.ArticleCollect;
import org.dgut.community.entity.FourmArticle;
import org.dgut.community.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CollectRepository extends JpaRepository<ArticleCollect, Long> {
    @Modifying
//    @Transactional
    @Query("delete from ArticleCollect  where articleId = ?1")
    void deleteByArticleId(Long id);
}
