package org.dgut.community.controller.article;

import org.dgut.community.entity.ArticleCollect;
import org.dgut.community.service.article.impl.CollectServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/articleCollect")
public class CollectController {
    private CollectServiceImpl service;
    private Map<String, Object> map = new HashMap<String, Object>();

    public CollectController(CollectServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/intercept/save")
    public ArticleCollect save(@RequestBody ArticleCollect entity){
        return service.save(entity);
    }

    @DeleteMapping("/intercept/deleteByArticleId/{id}")
    public Map deleteByArticleId(@PathVariable Long id){
        map.clear();
        map.put("message", service.deleteById(id));
        return map;
    }
}
