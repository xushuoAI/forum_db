package org.dgut.community.controller.news;

import org.dgut.community.entity.ArticleComment;
import org.dgut.community.entity.NewsComment;
import org.dgut.community.service.news.impl.NewsCommentServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/newsComment")
public class NewsCommentController {
    private NewsCommentServiceImpl service;
    private Map<String, Object> map = new HashMap<String, Object>();

    public NewsCommentController(NewsCommentServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/intercept/save/{newsId}")
    public NewsComment save(@PathVariable Long newsId, @RequestBody NewsComment entity){
        return service.save(newsId, entity);
    }

    @DeleteMapping("/intercept/deleteById/{id}")
    public Map deleteById(@PathVariable Long id){
        map.clear();
        map.put("message", service.deleteById(id));
        return map;
    }

    @PutMapping("/intercept/updateLike/{id}/{num}")
    public NewsComment updateLike(@PathVariable Long id, @PathVariable int num){
        return service.updateLike(id, num);
    }

    @PutMapping("/intercept/updateTop/{id}/{num}")
    public NewsComment updateTop(@PathVariable Long id, @PathVariable int num){
        return service.updateTop(id, num);
    }

    @GetMapping("/findByNewsId/{newsId}")
    public Page<NewsComment> findByNewsId(@PathVariable Long newsId, @RequestParam int num, @RequestParam(defaultValue = "15") int size){
        Pageable pageable = PageRequest.of(num, size);
        return service.findByNewsId(newsId, pageable);
    }
}
