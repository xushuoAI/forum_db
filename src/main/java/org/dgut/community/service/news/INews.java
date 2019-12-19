package org.dgut.community.service.news;

import org.dgut.community.entity.News;

public interface INews {
    News findById(Long id);

    News deleteById(Long id);

    News updateById(Long id);

    News save();
}
