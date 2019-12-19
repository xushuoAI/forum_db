package org.dgut.community.service.article.impl;

import org.dgut.community.entity.ArticleReply;
import org.dgut.community.repository.article.ReplyRepository;
import org.dgut.community.service.article.IReply;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements IReply {
    private ReplyRepository replyRepository;

    public ReplyServiceImpl(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @Override
    public ArticleReply findById(Long id) {
        return null;
    }

    @Override
    public ArticleReply deleteById(Long id) {
        return null;
    }

    @Override
    public ArticleReply updateById(Long id) {
        return null;
    }

    @Override
    public ArticleReply save() {
        return null;
    }
}
