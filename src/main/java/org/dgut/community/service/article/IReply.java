package org.dgut.community.service.article;

import org.dgut.community.entity.ArticleReply;

public interface IReply {
    ArticleReply findById(Long id);

    ArticleReply deleteById(Long id);

    ArticleReply updateById(Long id);

    ArticleReply save();
}
