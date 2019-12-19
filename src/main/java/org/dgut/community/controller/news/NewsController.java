package org.dgut.community.controller.news;

import org.dgut.community.service.news.impl.NewsServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {
    private NewsServiceImpl service;

    public NewsController(NewsServiceImpl service) {
        this.service = service;
    }
}
