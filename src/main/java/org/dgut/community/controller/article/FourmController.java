package org.dgut.community.controller.article;

import org.dgut.community.entity.FourmArticle;
import org.dgut.community.service.article.impl.FourmServiceImpl;
import org.dgut.community.util.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/articleFourm")
public class FourmController {
    private FourmServiceImpl service;
    private Map<String, Object> map = new HashMap<String, Object>();
    public FourmController(FourmServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/intercept/save/{id}")
    public FourmArticle save(@RequestBody FourmArticle entity, @PathVariable Long id){
        return service.save(entity, id);
    }

    @GetMapping("/findAll/{userId}")
    public Page<FourmArticle> findAll(@PathVariable Long userId, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Pageable pageable = PageRequest.of(num, size);
        return service.findAll(userId, pageable);
    }

    @GetMapping("/findByContent/{userId}")
    public Page<FourmArticle> findByContent(@PathVariable Long userId, @RequestBody FourmArticle fourmArticle, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Pageable pageable = PageRequest.of(num, size);
        return service.findByArticleContentLike(userId, fourmArticle.getArticleContent(), pageable);
    }

    @GetMapping("/findByUserId/{userId}")
    public Page<FourmArticle> findByUserId(@PathVariable Long userId, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Pageable pageable = PageRequest.of(num, size);
        return service.findByUserId(userId, pageable);
    }

    @PutMapping("/intercept/updateById/{id}")
    public FourmArticle updateById(@PathVariable Long id, @RequestBody FourmArticle article){
        return service.updateById(id, article);
    }

//    @PutMapping("/intercept/updateLike/{articleId}/{num}")
//    public FourmArticle updateLike(@PathVariable Long articleId, @PathVariable int num){
//        return service.updateLike(articleId, num);
//    }

    @DeleteMapping("/intercept/deleteById/{id}")
    public Map deleteById(@PathVariable Long id){
        map.clear();
        map.put("message", service.deleteById(id));
        return map;
    }
}
