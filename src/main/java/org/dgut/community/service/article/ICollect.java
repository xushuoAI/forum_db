package org.dgut.community.service.article;

import org.dgut.community.entity.ArticleCollect;
import org.dgut.community.entity.User;

public interface ICollect {
    ArticleCollect findByUserId(Long id);

    User findByArticleId(Long id);

    String deleteById(Long id);

    ArticleCollect save(ArticleCollect articleCollect);
}
