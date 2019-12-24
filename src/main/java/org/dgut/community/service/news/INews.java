package org.dgut.community.service.news;

import org.dgut.community.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface INews {
    Page<News> findByNewsContentLikeOrNewsTitleLike(String like, Pageable pageable);

    Page<News> findAll(Pageable pageable);

    Page<News> findByUser_userId(Long id, Pageable pageable);

    String deleteById(Long id);

    News updateLike(Long id, int num);

    News updateCollect(Long id, int num);

    News updateById(Long id, News newNews);

    News updateViewTimes(Long id);

    News save(News news, Long id, MultipartFile file);
}
