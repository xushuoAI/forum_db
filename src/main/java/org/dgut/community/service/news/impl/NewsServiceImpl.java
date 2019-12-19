package org.dgut.community.service.news.impl;

import org.dgut.community.entity.News;
import org.dgut.community.repository.news.NewsRepository;
import org.dgut.community.service.news.INews;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements INews {
    private NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News findById(Long id) {
        return null;
    }

    @Override
    public News deleteById(Long id) {
        return null;
    }

    @Override
    public News updateById(Long id) {
        return null;
    }

    @Override
    public News save() {
        return null;
    }
}
