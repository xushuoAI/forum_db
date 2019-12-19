package org.dgut.community.service.news.impl;

import org.dgut.community.entity.NewsReply;
import org.dgut.community.repository.news.ReplyRepository;
import org.dgut.community.service.news.IReply;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements IReply {
    private ReplyRepository replyRepository;

    public ReplyServiceImpl(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @Override
    public NewsReply findById(Long id) {
        return null;
    }

    @Override
    public NewsReply deleteById(Long id) {
        return null;
    }

    @Override
    public NewsReply updateById(Long id) {
        return null;
    }

    @Override
    public NewsReply save() {
        return null;
    }
}
