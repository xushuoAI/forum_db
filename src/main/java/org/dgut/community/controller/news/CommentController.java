package org.dgut.community.controller.news;

import org.dgut.community.service.news.impl.CommentServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newsComment")
public class CommentController {
    private CommentServiceImpl service;

    public CommentController(CommentServiceImpl service) {
        this.service = service;
    }
}
