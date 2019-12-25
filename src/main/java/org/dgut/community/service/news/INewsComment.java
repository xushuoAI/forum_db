package org.dgut.community.service.news;

import org.dgut.community.entity.NewsComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface INewsComment {

    Page<NewsComment> findByNewsId(Long newsId, Pageable pageable);

    ResponseEntity<?> deleteById(Long id);

    NewsComment updateLike(Long id, int num);

    NewsComment updateTop(Long id, int num);

    NewsComment save(Long newsId, NewsComment newsComment);
}
