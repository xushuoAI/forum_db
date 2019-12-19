package org.dgut.community.controller.article;

import org.dgut.community.service.article.impl.FourmServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articleFourm")
public class FourmController {
    private FourmServiceImpl service;

    public FourmController(FourmServiceImpl service) {
        this.service = service;
    }
}
