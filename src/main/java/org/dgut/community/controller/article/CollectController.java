package org.dgut.community.controller.article;

import org.dgut.community.service.article.impl.CollectServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articleCollect")
public class CollectController {
    private CollectServiceImpl service;

    public CollectController(CollectServiceImpl service) {
        this.service = service;
    }
}
