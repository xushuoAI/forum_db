package org.dgut.community.service.news;

import org.dgut.community.entity.NewsReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface INewsReply {

    Page<NewsReply> findByCommentId(Long commentId, Pageable pageable);

    NewsReply findById(Long id);

    ResponseEntity<?> deleteById(Long id);

    NewsReply updateById(Long id);

    NewsReply save(Long id, NewsReply newsReply);
}
