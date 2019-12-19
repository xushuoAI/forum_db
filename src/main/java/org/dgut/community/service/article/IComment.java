package org.dgut.community.service.article;

import org.dgut.community.entity.ArticleComment;

public interface IComment {
    ArticleComment findById(Long id);

    ArticleComment deleteById(Long id);

    ArticleComment updateById(Long id);

    ArticleComment save();
}
