package org.dgut.community.controller.article;

import org.dgut.community.entity.ArticleCollect;
import org.dgut.community.entity.FourmArticle;
import org.dgut.community.entity.User;
import org.dgut.community.resultenum.Result;
import org.dgut.community.service.article.impl.CollectServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Result> save(@RequestBody ArticleCollect entity){
        return service.save(entity);
    }

    @DeleteMapping("/intercept/deleteByArticleId")
    public ResponseEntity<?> deleteByArticleId(@RequestBody ArticleCollect collect){
        return service.deleteById(collect.getArticleId(), collect.getUserId());
    }

    @GetMapping("/intercept/findUser/{articleId}")
    public List<FourmArticle> findArticle(@PathVariable Long articleId, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Sort sort = Sort.by(Sort.Direction.DESC, "collectId");
        Pageable pageable = PageRequest.of(num, size, sort);
        return service.findByUserId(articleId, pageable);
    }

    @GetMapping("/intercept/findArticle/{userId}")
    public List<User> findUser(@PathVariable Long userId, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Sort sort = Sort.by(Sort.Direction.DESC, "collectId");
        Pageable pageable = PageRequest.of(num, size, sort);
        return service.findByArticleId(userId, pageable);
    }
}
