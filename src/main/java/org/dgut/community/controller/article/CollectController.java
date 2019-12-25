package org.dgut.community.controller.article;

import org.dgut.community.entity.ArticleCollect;
import org.dgut.community.entity.FourmArticle;
import org.dgut.community.entity.User;
import org.dgut.community.service.article.impl.CollectServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @GetMapping("/intercept/findUser/{articleId}")
    public List<FourmArticle> findArticle(@PathVariable Long articleId, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Pageable pageable = PageRequest.of(num, size);
        return service.findByUserId(articleId, pageable);
    }

    @GetMapping("/intercept/findArticle/{userId}")
    public List<User> findUser(@PathVariable Long userId, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Pageable pageable = PageRequest.of(num, size);
        return service.findByArticleId(userId, pageable);
    }
}
