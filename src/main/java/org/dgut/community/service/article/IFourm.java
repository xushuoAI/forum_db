package org.dgut.community.service.article;

import org.dgut.community.entity.FourmArticle;

public interface IFourm {
    FourmArticle findById(Long id);

    FourmArticle deleteById(Long id);

    FourmArticle updateById(Long id);

    FourmArticle save();
}
