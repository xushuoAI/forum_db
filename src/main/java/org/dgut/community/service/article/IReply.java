package org.dgut.community.service.article;

import org.dgut.community.entity.ArticleReply;
import org.dgut.community.resultenum.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IReply {

    Page<ArticleReply> findByCommentId(Long commentId, Pageable pageable);

    ArticleReply findById(Long id);

    ResponseEntity<?> deleteById(Long id);

    ResponseEntity<Result> save(Long id, ArticleReply articleReply);
}
