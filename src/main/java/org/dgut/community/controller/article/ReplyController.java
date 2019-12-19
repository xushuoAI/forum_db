package org.dgut.community.controller.article;

import org.dgut.community.service.article.impl.ReplyServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articleReply")
public class ReplyController {
    private ReplyServiceImpl service;

    public ReplyController(ReplyServiceImpl service) {
        this.service = service;
    }
}
