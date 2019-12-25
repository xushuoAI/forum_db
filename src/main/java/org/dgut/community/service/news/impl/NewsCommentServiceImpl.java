package org.dgut.community.service.news.impl;

import org.dgut.community.NotFoundException;
import org.dgut.community.entity.NewsComment;
import org.dgut.community.repository.news.NewsCommentRepository;
import org.dgut.community.repository.news.NewsRepository;
import org.dgut.community.service.news.INewsComment;
import org.dgut.community.util.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NewsCommentServiceImpl implements INewsComment {
    private NewsCommentRepository newsCommentRepository;
    private NewsRepository newsRepository;

    public NewsCommentServiceImpl(NewsCommentRepository newsCommentRepository, NewsRepository newsRepository) {
        this.newsCommentRepository = newsCommentRepository;
        this.newsRepository = newsRepository;
    }

    @Override
    public Page<NewsComment> findByNewsId(Long newsId, Pageable pageable) {
        return newsCommentRepository.findByNews_newsId(newsId, pageable);
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        return newsCommentRepository.findById(id).map(newsComment -> {
            newsComment.setNews(null);
            newsCommentRepository.delete(newsComment);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new NotFoundException("没有该帖子"));
    }

    @Override
    public NewsComment updateLike(Long id, int num) {
        return newsCommentRepository.findById(id).map(newsComment -> {
            if (num == 1){
                newsComment.setNewsCommentLike(newsComment.getNewsCommentLike() + 1);
                return newsCommentRepository.save(newsComment);
            }else {
                if (newsComment.getNewsCommentLike() == 0){
                    return newsCommentRepository.save(newsComment);
                }
                newsComment.setNewsCommentLike(newsComment.getNewsCommentLike() - 1);
                return newsCommentRepository.save(newsComment);
            }
        }).orElseThrow(()-> new NotFoundException("没有该评论"));
    }

    @Override
    public NewsComment updateTop(Long id, int num) {
        return newsCommentRepository.findById(id).map(newsComment -> {
            if(num == 1){
                newsComment.setToTop(1);
            }else {
                newsComment.setToTop(0);
            }
            return newsCommentRepository.save(newsComment);
        }).orElseThrow(()-> new NotFoundException("没有该评论"));
    }

    @Override
    public NewsComment save(Long newsId, NewsComment newsComment) {
        return newsRepository.findById(newsId).map(news -> {
            newsComment.setNews(news);
            newsComment.setNewsCommentCreateTime(LocalDate.parse(Util.getTime()));
            return newsCommentRepository.save(newsComment);
        }).orElseThrow(()-> new NotFoundException("没有该新闻"));
    }
}
