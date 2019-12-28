package org.dgut.community.service.news.impl;

import org.dgut.community.NotFoundException;
import org.dgut.community.entity.FourmArticle;
import org.dgut.community.entity.News;
import org.dgut.community.repository.news.NewsRepository;
import org.dgut.community.repository.user.UserRepository;
import org.dgut.community.resultenum.ResultEnum;
import org.dgut.community.service.news.INews;
import org.dgut.community.util.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements INews {
    private NewsRepository newsRepository;
    private UserRepository userRepository;

    public NewsServiceImpl(NewsRepository newsRepository, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<News> findByNewsContentLikeOrNewsTitleLike(String like, Pageable pageable) {
        Page<News> news = newsRepository.findByNewsContentLikeOrNewsTitleLike("%" + like + "%", "%" + like + "%", pageable);
//        String url = Util.getUrl();
        for (News news1 : news) {
//            String userHeadImg = "/" + news1.getUser().getUserHeadImg();
//            userHeadImg = userHeadImg.substring(userHeadImg.lastIndexOf("/") + 1);
//            news1.getUser().setUserHeadImg(null);
//            if (news1.getUser().getUserHeadImg() != null){
//                news1.getUser().setUserHeadImg(url + news1.getUser().getUserName() + "/" + userHeadImg);
//            }else {
//                news1.getUser().setUserHeadImg(url + "abc.jpg");
//            }
////            news1.getUser().setUserPassword(null);
//            if (news1.getNewsPicture() != null) {
//                news1.setNewsPicture(url + news1.getUser().getUserName() + "/" + news1.getNewsPicture());
//            }
            news1.getUser().setUserPassword(null);
        }
        return news;
    }

    @Override
    public Page<News> findAll(Pageable pageable) {
        Page<News> newses = newsRepository.findAll(pageable);
//        String url = Util.getUrl();
        for (News news : newses) {
//            String userHeadImg = "/" + news.getUser().getUserHeadImg();
//            userHeadImg = userHeadImg.substring(userHeadImg.lastIndexOf("/") + 1);
//            news.getUser().setUserHeadImg(null);
//            if (news.getUser().getUserHeadImg() != null){
//                news.getUser().setUserHeadImg(url + news.getUser().getUserName() + "/" + userHeadImg);
//            }else {
//                news.getUser().setUserHeadImg(url + "abc.jpg");
//            }
////            news.getUser().setUserPassword(null);
//            if (news.getNewsPicture() != null) {
//                news.setNewsPicture(url + news.getUser().getUserName() + "/" + news.getNewsPicture());
//            }
            news.getUser().setUserPassword(null);
        }
        return newses;
    }

    @Override
    public Page<News> findByUser_userId(Long id, Pageable pageable) {
        Page<News> newses = newsRepository.findByUser_userId(id, pageable);
        for (News news : newses){
//            if (news.getNewsPicture() != null){
//                news.setNewsPicture(Util.getUrl() + news.getUser().getUserName() + "/" + news.getNewsPicture());
//            }
            news.setUser(null);
        }
        return newses;
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        return newsRepository.findById(id).map(news -> {
            news.setUser(null);
            newsRepository.delete(news);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public News updateLike(Long id, int num) {
        return newsRepository.findById(id).map(news -> {
            if (num == 1) {
                news.setNewsLike(news.getNewsLike() + 1);
                return newsRepository.save(news);
            } else {
                if (news.getNewsLike() == 0) {
                    return newsRepository.save(news);
                }
                news.setNewsLike(news.getNewsLike() - 1);
                News news1 = newsRepository.save(news);
                news1.getUser().setUserPassword(null);
                return news1;
            }
        }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public News updateCollect(Long id, int num) {
        return newsRepository.findById(id).map(news -> {
            if (num == 1){
                news.setNewsCollect(news.getNewsCollect() + 1);
            }else {
                if (news.getNewsCollect() != 0){
                    news.setNewsCollect(news.getNewsCollect() - 1);
                }
            }
            News news1 = newsRepository.save(news);
            news1.getUser().setUserPassword(null);
            return news1;
        }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public News updateById(Long id, News newNews) {
        return newsRepository.findById(id).map(news -> {
            news.setNewsContent(newNews.getNewsContent());
//            news.setNewsPicture(newNews.getNewsPicture());
            news.setNewsTitle(newNews.getNewsTitle());
            News news1 = newsRepository.save(news);
            news1.getUser().setUserPassword(null);
            return news1;
        }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public News updateViewTimes(Long id) {
        return newsRepository.findById(id).map(news -> {
            news.setNewsViewTimes(news.getNewsViewTimes() + 1);
            News news1 = newsRepository.save(news);
            news1.getUser().setUserPassword(null);
            return news1;
        }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public News save(News news, Long id) {
//        String date = "" + new Date();
//        date = DigestUtils.md5DigestAsHex(date.getBytes());
//        String name = "news1" + date;
        return userRepository.findById(id).map(user -> {
//            if (file != null) {
//                Util.upload(file, user.getUserName(), name);
//                news.setNewsPicture(name + file.getOriginalFilename());
//            }
            news.setNewsPicture(Util.uploadBase64Image("news", news.getNewsPicture()));
//            news.setPublicTime(LocalDate.parse(Util.getTime()));
            news.setUser(user);
            News news1 = newsRepository.save(news);
            news1.getUser().setUserPassword(null);
            return news1;
        }).orElseThrow(() -> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }
}
