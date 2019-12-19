package org.dgut.community.controller.article;

import org.dgut.community.service.article.impl.CommentServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articleComment")
public class CommentController {
    private CommentServiceImpl service;

    public CommentController(CommentServiceImpl service) {
        this.service = service;
    }
}
