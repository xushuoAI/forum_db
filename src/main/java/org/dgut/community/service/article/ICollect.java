package org.dgut.community.service.article;

import org.dgut.community.entity.ArticleCollect;
import org.dgut.community.entity.FourmArticle;
import org.dgut.community.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICollect {
    List<FourmArticle> findByUserId(Long id, Pageable pageable);

    List<User> findByArticleId(Long id, Pageable pageable);

    ResponseEntity<?> deleteById(Long id);

    ArticleCollect save(ArticleCollect articleCollect);
}
