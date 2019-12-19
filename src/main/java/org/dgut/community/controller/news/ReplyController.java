package org.dgut.community.controller.news;

import org.dgut.community.service.news.impl.ReplyServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newsReply")
public class ReplyController {
    private ReplyServiceImpl service;

    public ReplyController(ReplyServiceImpl service) {
        this.service = service;
    }
}
