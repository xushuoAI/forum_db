package org.dgut.community.service.news;

import org.dgut.community.entity.NewsReply;

public interface IReply {
    NewsReply findById(Long id);

    NewsReply deleteById(Long id);

    NewsReply updateById(Long id);

    NewsReply save();
}
