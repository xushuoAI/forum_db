package org.dgut.community.service.article.impl;

import org.dgut.community.NotFoundException;
import org.dgut.community.entity.ArticleCollect;
import org.dgut.community.entity.FourmArticle;
import org.dgut.community.entity.User;
import org.dgut.community.repository.article.CollectRepository;
import org.dgut.community.repository.article.FourmRepository;
import org.dgut.community.repository.user.UserRepository;
import org.dgut.community.service.article.ICollect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public List<FourmArticle> findByUserId(Long id, Pageable pageable) {
        Page<ArticleCollect> articleCollects = collectRepository.findByUserId(id, pageable);
        List<Long> userId = new ArrayList<Long>();
        for (ArticleCollect articleCollect : articleCollects){
            userId.add(articleCollect.getArticleId());
        }
        Iterable<Long> longs = new Iterable<Long>() {
            @Override
            public Iterator<Long> iterator() {
                return userId.iterator();
            }
        };
        List<FourmArticle> fourmArticles = fourmRepository.findAllById(longs);
        for (FourmArticle fourmArticle : fourmArticles){
            fourmArticle.getUser().setUserPassword(null);
        }
        return fourmArticles;
    }

    @Override
    public List<User> findByArticleId(Long id, Pageable pageable) {
        Page<ArticleCollect> articleCollects = collectRepository.findByArticleId(id, pageable);
        List<Long> userId = new ArrayList<Long>();
        for (ArticleCollect articleCollect : articleCollects){
            userId.add(articleCollect.getUserId());
        }
        Iterable<Long> longs = new Iterable<Long>() {
            @Override
            public Iterator<Long> iterator() {
                return userId.iterator();
            }
        };
        List<User> users = userRepository.findAllById(longs);
        for (User user : users){
            user.setUserPassword(null);
        }
        return users;
    }

    @Override
    public ResponseEntity<?> deleteById(Long articleId, Long userId) {
        return fourmRepository.findById(articleId).map(article -> {
            article.setArticleCollect(article.getArticleCollect() - 1);
            fourmRepository.save(article);
            ArticleCollect collect = collectRepository.findByArticleIdAndUserId(articleId, userId);
            collectRepository.delete(collect);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new NotFoundException("没有该帖子"));
    }

    @Override
    public ArticleCollect save(ArticleCollect articleCollect) {
        return fourmRepository.findById(articleCollect.getArticleId()).map(article -> {
            article.setArticleCollect(article.getArticleCollect() + 1);
            fourmRepository.save(article);
            return collectRepository.save(articleCollect);
        }).orElseThrow(()-> new NotFoundException("没有该帖子"));
    }
}
