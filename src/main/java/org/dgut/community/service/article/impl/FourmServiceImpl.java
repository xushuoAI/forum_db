package org.dgut.community.service.article.impl;

import org.dgut.community.entity.FourmArticle;
import org.dgut.community.repository.article.FourmRepository;
import org.dgut.community.service.article.IFourm;
import org.springframework.stereotype.Service;

@Service
public class FourmServiceImpl implements IFourm {
    private FourmRepository fourmRepository;

    public FourmServiceImpl(FourmRepository fourmRepository) {
        this.fourmRepository = fourmRepository;
    }

    @Override
    public FourmArticle findById(Long id) {
        return null;
    }

    @Override
    public FourmArticle deleteById(Long id) {
        return null;
    }

    @Override
    public FourmArticle updateById(Long id) {
        return null;
    }

    @Override
    public FourmArticle save() {
        return null;
    }
}
