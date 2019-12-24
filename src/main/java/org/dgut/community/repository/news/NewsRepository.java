package org.dgut.community.repository.news;

import org.dgut.community.entity.FourmArticle;
import org.dgut.community.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findByNewsContentLikeOrNewsTitleLike(String content, String title, Pageable pageable);
    Page<News> findAll(Pageable pageable);
    Page<News> findByUser_userId(Long id, Pageable pageable);
}
