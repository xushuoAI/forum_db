package org.dgut.community.service.article.impl;

import org.dgut.community.entity.FourmArticle;
import org.dgut.community.repository.article.FourmRepository;
import org.dgut.community.repository.user.UserRepository;
import org.dgut.community.service.article.IFourm;
import org.dgut.community.util.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class FourmServiceImpl implements IFourm {
    private FourmRepository fourmRepository;
    private UserRepository userRepository;

    public FourmServiceImpl(FourmRepository fourmRepository, UserRepository userRepository) {
        this.fourmRepository = fourmRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<FourmArticle> findByArticleContentLike(String articleContent, Pageable pageable) {
        Page<FourmArticle> articles = fourmRepository.findByArticleContentLike("%" + articleContent + "%", pageable);
        String url = Util.getUrl();
        for (FourmArticle article : articles) {
            String userHeadImg = "/" + article.getUser().getUserHeadImg();
            userHeadImg = userHeadImg.substring(userHeadImg.lastIndexOf("/") + 1);
            article.getUser().setUserHeadImg(null);
            if (article.getUser().getUserHeadImg() != null){
                article.getUser().setUserHeadImg(url + article.getUser().getUserName() + "/" + userHeadImg);
            }else {
                article.getUser().setUserHeadImg(url + "abc.jpg");
            }
//            article.getUser().setUserPassword(null);
            if (article.getArticlePhoto() != null) {
                article.setArticlePhoto(url + article.getUser().getUserName() + "/" + article.getArticlePhoto());
            }
        }
        return articles;
    }

    @Override
    public Page<FourmArticle> findAll(Pageable pageable) {
        Page<FourmArticle> articles = fourmRepository.findAll(pageable);
        String url = Util.getUrl();
        for (FourmArticle article : articles) {
            String userHeadImg = "/" + article.getUser().getUserHeadImg();
            userHeadImg = userHeadImg.substring(userHeadImg.lastIndexOf("/") + 1);
            article.getUser().setUserHeadImg(null);
            if (article.getUser().getUserHeadImg() != null){
                article.getUser().setUserHeadImg(url + article.getUser().getUserName() + "/" + userHeadImg);
            }else {
                article.getUser().setUserHeadImg(url + "abc.jpg");
            }
//            article.getUser().setUserPassword(null);
            if (article.getArticlePhoto() != null) {
                article.setArticlePhoto(url + article.getUser().getUserName() + "/" + article.getArticlePhoto());
            }
        }
        return articles;
    }

    @Override
    public Page<FourmArticle> findByUserId(Long id, Pageable pageable) {
        Page<FourmArticle> articles = fourmRepository.findByUser_userId(id, pageable);
        for (FourmArticle article : articles){
            if (article.getArticlePhoto() != null){
                article.setArticlePhoto(Util.getUrl() + article.getUser().getUserName() + "/" + article.getArticlePhoto());
            }
            article.setUser(null);
        }
        return articles;
    }

    @Override
    public String deleteById(Long id) {
        return fourmRepository.findById(id).map(fourmArticle -> {
            userRepository.findById(fourmArticle.getUser().getUserId()).map(user -> {
                user.setUserArticles(user.getUserArticles() - 1);
                userRepository.save(user);
                fourmArticle.setUser(null);
                fourmRepository.delete(fourmArticle);
                return "删除成功";
            });
            return "删除成功";
        }).orElseThrow(() -> new RuntimeException("没有该Id"));
    }

    @Override
    public FourmArticle updateById(Long id, FourmArticle newFourmArticle) {
        return fourmRepository.findById(id).map(fourmArticle -> {
            fourmArticle.setArticleContent(newFourmArticle.getArticleContent());
            return fourmRepository.save(fourmArticle);
        }).orElseThrow(() -> new RuntimeException("没有该帖子"));
    }

    @Override
    public FourmArticle updateLike(Long id, int num) {
        return fourmRepository.findById(id).map(article -> {
            if (num == 1) {
                article.setArticleLike(article.getArticleLike() + 1);
                return fourmRepository.save(article);
            } else {
                if (article.getArticleLike() == 0) {
                    return fourmRepository.save(article);
                }
                article.setArticleLike(article.getArticleLike() - 1);
                return fourmRepository.save(article);
            }
        }).orElseThrow(() -> new RuntimeException("没有该帖子"));
    }

    @Override
    public FourmArticle save(FourmArticle fourmArticle, Long id, MultipartFile file) {
        String date = "" + new Date();
        date = DigestUtils.md5DigestAsHex(date.getBytes());
        String name = "article" + date;
        return userRepository.findById(id).map(user -> {
            if (file != null) {
                Util.upload(file, user.getUserName(), name);
                fourmArticle.setArticlePhoto(name + file.getOriginalFilename());
            }
            fourmArticle.setArticlePublicTime(LocalDate.parse(Util.getTime()));
            user.setUserArticles(user.getUserArticles() + 1);
            fourmArticle.setUser(user);
            FourmArticle article = fourmRepository.save(fourmArticle);
            article.getUser().setUserPassword(null);
            return article;
        }).orElseThrow(() -> new RuntimeException("没有该Id"));
    }
}
