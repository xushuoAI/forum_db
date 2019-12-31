package org.dgut.community.service.article;

import org.dgut.community.entity.ArticleReply;
import org.dgut.community.resultenum.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IReply {
    Page<ArticleReply> findByComment_Article_User_UserId(Long userId, Pageable pageable);

    Page<ArticleReply> findByToUserName(String userName, Pageable pageable);

    Page<ArticleReply> findByCommentId(Long commentId, Pageable pageable);

    ArticleReply findById(Long id);

    ResponseEntity<?> deleteById(Long id);

    ResponseEntity<Result> save(Long id, ArticleReply articleReply);
}
