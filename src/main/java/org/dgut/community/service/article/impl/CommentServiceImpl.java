package org.dgut.community.service.article.impl;

import org.dgut.community.entity.ArticleComment;
import org.dgut.community.repository.article.CommentRepository;
import org.dgut.community.service.article.IComment;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements IComment {
    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public ArticleComment findById(Long id) {
        return null;
    }

    @Override
    public ArticleComment deleteById(Long id) {
        return null;
    }

    @Override
    public ArticleComment updateById(Long id) {
        return null;
    }

    @Override
    public ArticleComment save() {
        return null;
    }
}
