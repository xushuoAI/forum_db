package org.dgut.community.controller.article;

import org.dgut.community.entity.ArticleComment;
import org.dgut.community.service.article.impl.CommentServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/articleComment")
public class CommentController {
    private CommentServiceImpl service;
    private Map<String, Object> map = new HashMap<String, Object>();

    public CommentController(CommentServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/intercept/save/{articleId}")
    public ArticleComment save(@PathVariable Long articleId, @RequestBody ArticleComment entity){
        return service.save(articleId, entity);
    }

    @DeleteMapping("/intercept/deleteById/{id}")
    public Map<String, Object> deleteById(@PathVariable Long id){
        map.clear();
        map.put("message", service.deleteById(id));
        return map;
    }

    @PutMapping("/intercept/updateLike/{id}/{num}")
    public ArticleComment updateLike(@PathVariable Long id, @PathVariable int num){
        return service.updateLike(id, num);
    }

    @PutMapping("/intercept/updateTop/{id}/{num}")
    public ArticleComment updateTop(@PathVariable Long id, @PathVariable int num){
        return service.updateTop(id, num);
    }

    @GetMapping("/findByArticleId/{articleId}")
    public Page<ArticleComment> findByArticleId(@PathVariable Long articleId, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Pageable pageable = PageRequest.of(num, size);
        return service.findByArticleId(articleId, pageable);
    }
}
