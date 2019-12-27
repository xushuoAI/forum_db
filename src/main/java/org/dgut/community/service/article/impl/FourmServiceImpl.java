package org.dgut.community.service.article.impl;

import org.dgut.community.NotFoundException;
import org.dgut.community.entity.FourmArticle;
import org.dgut.community.repository.article.CollectRepository;
import org.dgut.community.repository.article.FourmRepository;
import org.dgut.community.repository.article.LikeRepository;
import org.dgut.community.repository.user.UserRepository;
import org.dgut.community.resultenum.ResultEnum;
import org.dgut.community.service.article.IFourm;
import org.dgut.community.util.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FourmServiceImpl implements IFourm {
    private FourmRepository fourmRepository;
    private UserRepository userRepository;
    private LikeRepository likeRepository;
    private CollectRepository collectRepository;

    public FourmServiceImpl(FourmRepository fourmRepository, UserRepository userRepository, LikeRepository likeRepository, CollectRepository collectRepository) {
        this.fourmRepository = fourmRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.collectRepository = collectRepository;
    }

    @Override
    public Page<FourmArticle> findByArticleContentLike(Long userId, String articleContent, Pageable pageable) {
        Page<FourmArticle> articles = fourmRepository.findByArticleContentLike("%" + articleContent + "%", pageable);
        return addLikeAndCollect(articles, userId);
    }

    @Override
    public Page<FourmArticle> findAll(Long userId, Pageable pageable) {
        Page<FourmArticle> articles = fourmRepository.findAll(pageable);
        return addLikeAndCollect(articles, userId);
    }

    @Override
    public Page<FourmArticle> findByUserId(Long id, Pageable pageable) {
        Page<FourmArticle> articles = fourmRepository.findByUser_userId(id, pageable);
        for (FourmArticle article : articles){
//            if (article.getArticlePhoto() != null){
//                article.setArticlePhoto(Util.getUrl() + article.getUser().getUserName() + "/" + article.getArticlePhoto());
//            }
            article.setUser(null);
        }
        return articles;
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        return fourmRepository.findById(id).map(fourmArticle -> {
            return userRepository.findById(fourmArticle.getUser().getUserId()).map(user -> {
                user.setUserArticles(user.getUserArticles() - 1);
                userRepository.save(user);
                fourmArticle.setUser(null);
                fourmRepository.delete(fourmArticle);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
        }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public FourmArticle updateById(Long id, FourmArticle newFourmArticle) {
        return fourmRepository.findById(id).map(fourmArticle -> {
            fourmArticle.setArticleContent(newFourmArticle.getArticleContent());
            fourmArticle = fourmRepository.save(fourmArticle);
            fourmArticle.getUser().setUserPassword(null);
            return fourmArticle;
        }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

//    @Override
//    public FourmArticle updateLike(Long id, int num) {
//        return fourmRepository.findById(id).map(article -> {
//            if (num == 1) {
//                article.setArticleLike(article.getArticleLike() + 1);
//            } else {
//                if (article.getArticleLike() == 0) {
//                    article = fourmRepository.save(article);
//                    article.getUser().setUserPassword(null);
//                    return article;
//                }
//                article.setArticleLike(article.getArticleLike() - 1);
//            }
//            article = fourmRepository.save(article);
//            article.getUser().setUserPassword(null);
//            return article;
//        }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
//    }

    @Override
    public FourmArticle save(FourmArticle fourmArticle, Long id) {
//        String date = "" + new Date();
//        date = DigestUtils.md5DigestAsHex(date.getBytes());
//        String name = "article" + date;
        return userRepository.findById(id).map(user -> {
//            if (file != null) {
//                Util.upload(file, user.getUserName(), name);
//                fourmArticle.setArticlePhoto(name + file.getOriginalFilename());
//            }
            fourmArticle.setArticlePhoto(Util.uploadBase64Image("article", fourmArticle.getArticlePhoto()));
            fourmArticle.setArticlePublicTime(LocalDate.parse(Util.getTime()));
            user.setUserArticles(user.getUserArticles() + 1);
            userRepository.save(user);
            fourmArticle.setUser(user);
            FourmArticle article = fourmRepository.save(fourmArticle);
            article.getUser().setUserPassword(null);
            return article;
        }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    Page<FourmArticle> addLikeAndCollect(Page<FourmArticle> articles, Long userId){
        for (FourmArticle article : articles) {
            if (likeRepository.findByArticle_ArticleIdAndUserId(article.getArticleId(), userId) != null){
                article.setIsLike(1);
            }
            if (collectRepository.findByArticleIdAndUserId(article.getArticleId(), userId) != null){
                article.setCollect(1);
            }
            article.getUser().setUserPassword(null);
        }
        return articles;
    }
}
