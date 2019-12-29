package org.dgut.community.service.article.impl;

import org.apache.commons.lang3.StringUtils;
import org.dgut.community.NotFoundException;
import org.dgut.community.entity.FourmArticle;
import org.dgut.community.entity.Image;
import org.dgut.community.repository.article.CollectRepository;
import org.dgut.community.repository.article.FourmRepository;
import org.dgut.community.repository.article.LikeRepository;
import org.dgut.community.repository.user.FollowRepository;
import org.dgut.community.repository.user.UserRepository;
import org.dgut.community.resultenum.Result;
import org.dgut.community.resultenum.ResultEnum;
import org.dgut.community.service.article.IFourm;
import org.dgut.community.util.ResultUtil;
import org.dgut.community.util.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FourmServiceImpl implements IFourm {
    private FourmRepository fourmRepository;
    private UserRepository userRepository;
    private LikeRepository likeRepository;
    private CollectRepository collectRepository;
    private FollowRepository followRepository;

    public FourmServiceImpl(FourmRepository fourmRepository, UserRepository userRepository, LikeRepository likeRepository, CollectRepository collectRepository, FollowRepository followRepository) {
        this.fourmRepository = fourmRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.collectRepository = collectRepository;
        this.followRepository = followRepository;
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
            article.setUser(null);
            if (article.getArticlePhoto() != null){
                String[] strings = article.getArticlePhoto().split(",");
                List<Image> photo = new ArrayList<>();
                for (String string : strings){
                    Image image = new Image();
                    image.setImageUrl(string);
                    photo.add(image);
                }
                article.setPhotos(photo);
            }
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
                if (fourmArticle.getArticlePhoto() != null){
                    Util.deleteFile(fourmArticle.getArticlePhoto());
                }
                fourmRepository.delete(fourmArticle);
                return ResponseEntity.ok(ResultUtil.success());
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

    @Override
    public ResponseEntity<Result> save(FourmArticle fourmArticle, Long id) {
        return userRepository.findById(id).map(user -> {
            fourmArticle.setArticlePhoto(upload(fourmArticle.getArticlePhoto()));
            user.setUserArticles(user.getUserArticles() + 1);
            userRepository.save(user);
            fourmArticle.setUser(user);
            FourmArticle article = fourmRepository.save(fourmArticle);
            article.getUser().setUserPassword(null);
            if (article.getArticlePhoto() != null){
                String[] strings = article.getArticlePhoto().split(",");
                List<Image> photo = new ArrayList<>();
                for (String string : strings){
                    Image image = new Image();
                    image.setImageUrl(string);
                    photo.add(image);
                }
                article.setPhotos(photo);
            }
            return ResponseEntity.ok(ResultUtil.success(article));
        }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    Page<FourmArticle> addLikeAndCollect(Page<FourmArticle> articles, Long userId){
        if (userId == 0){
            for (FourmArticle article : articles) {
                article.getUser().setUserPassword(null);
                if (article.getArticlePhoto() != null){
                    String[] strings = article.getArticlePhoto().split(",");
                    List<Image> photo = new ArrayList<>();
                    for (String string : strings){
                        Image image = new Image();
                        image.setImageUrl(string);
                        photo.add(image);
                    }
                    article.setPhotos(photo);
                }
            }
        }else {
            for (FourmArticle article : articles) {
                if (likeRepository.findByArticle_ArticleIdAndUserId(article.getArticleId(), userId) != null){
                    article.setIsLike(1);
                }
                if (collectRepository.findByArticleIdAndUserId(article.getArticleId(), userId) != null){
                    article.setCollect(1);
                }
                if (followRepository.findByFansIdAndStarId(userId, article.getUser().getUserId()) != null){
                    article.getUser().setIsFocus(1);
                }
                article.getUser().setUserPassword(null);
                if (article.getArticlePhoto() != null){
                    String[] strings = article.getArticlePhoto().split(",");
                    List<Image> photo = new ArrayList<>();
                    for (String string : strings){
                        Image image = new Image();
                        image.setImageUrl(string);
                        photo.add(image);
                    }
                    article.setPhotos(photo);
                }
            }
        }

        return articles;
    }

    String upload(String base64){
        String[] splits = base64.split("@");
        List<String> joins = new ArrayList<>();
        for (String split : splits){
            split = Util.uploadBase64Image("article", split);
            joins.add(split);
        }
        String join = StringUtils.join(joins, ",");
        System.out.println(join);
        return join;
    }
}
