package org.dgut.community.service.news;

import org.dgut.community.entity.NewsComment;

public interface IComment {
    NewsComment findById(Long id);

    NewsComment deleteById(Long id);

    NewsComment updateById(Long id);

    NewsComment save();
}
