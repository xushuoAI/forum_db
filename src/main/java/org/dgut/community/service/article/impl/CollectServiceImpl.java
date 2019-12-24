package org.dgut.community.service.article.impl;

import org.dgut.community.entity.ArticleCollect;
import org.dgut.community.entity.User;
import org.dgut.community.repository.article.CollectRepository;
import org.dgut.community.repository.article.FourmRepository;
import org.dgut.community.repository.user.UserRepository;
import org.dgut.community.service.article.ICollect;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl implements ICollect {
    private CollectRepository collectRepository;
    private UserRepository userRepository;
    private FourmRepository fourmRepository;

    public CollectServiceImpl(CollectRepository collectRepository, UserRepository userRepository, FourmRepository fourmRepository) {
        this.collectRepository = collectRepository;
        this.userRepository = userRepository;
        this.fourmRepository = fourmRepository;
    }

    @Override
    public ArticleCollect findByUserId(Long id) {
        return null;
    }

    @Override
    public User findByArticleId(Long id) {
        return null;
    }

    @Override
    public String deleteById(Long articleId) {
        return fourmRepository.findById(articleId).map(article -> {
            article.setArticleCollect(article.getArticleCollect() - 1);
            fourmRepository.save(article);
            collectRepository.deleteByArticleId(articleId);
            return "删除成功";
        }).orElseThrow(()-> new RuntimeException("没有该帖子"));
    }

    @Override
    public ArticleCollect save(ArticleCollect articleCollect) {
        return fourmRepository.findById(articleCollect.getArticleId()).map(article -> {
            article.setArticleCollect(article.getArticleCollect() + 1);
            fourmRepository.save(article);
            return collectRepository.save(articleCollect);
        }).orElseThrow(()-> new RuntimeException("没有该帖子"));
    }
}
