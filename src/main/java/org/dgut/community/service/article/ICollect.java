package org.dgut.community.service.article;

import org.dgut.community.entity.ArticleCollect;

public interface ICollect {
    ArticleCollect findById(Long id);

    ArticleCollect deleteById(Long id);

    ArticleCollect updateById(Long id);

    ArticleCollect save();
}
