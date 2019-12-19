package org.dgut.community.service.news.impl;

import org.dgut.community.entity.NewsComment;
import org.dgut.community.repository.news.CommentRepository;
import org.dgut.community.service.news.IComment;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements IComment {
    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public NewsComment findById(Long id) {
        return null;
    }

    @Override
    public NewsComment deleteById(Long id) {
        return null;
    }

    @Override
    public NewsComment updateById(Long id) {
        return null;
    }

    @Override
    public NewsComment save() {
        return null;
    }
}
