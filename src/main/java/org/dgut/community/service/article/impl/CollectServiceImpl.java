package org.dgut.community.service.article.impl;

import org.dgut.community.entity.ArticleCollect;
import org.dgut.community.repository.article.CollectRepository;
import org.dgut.community.service.article.ICollect;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl implements ICollect {
    private CollectRepository collectRepository;

    public CollectServiceImpl(CollectRepository collectRepository) {
        this.collectRepository = collectRepository;
    }

    @Override
    public ArticleCollect findById(Long id) {
        return null;
    }

    @Override
    public ArticleCollect deleteById(Long id) {
        return null;
    }

    @Override
    public ArticleCollect updateById(Long id) {
        return null;
    }

    @Override
    public ArticleCollect save() {
        return null;
    }
}
