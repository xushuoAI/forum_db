package org.dgut.community.controller.news;

import org.dgut.community.entity.ArticleReply;
import org.dgut.community.entity.NewsReply;
import org.dgut.community.service.news.impl.NewsReplyServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/newsReply")
public class NewsReplyController {
    private NewsReplyServiceImpl service;
    private Map<String, Object> map = new HashMap<String, Object>();

    public NewsReplyController(NewsReplyServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/intercept/save/{commentId}")
    public NewsReply save(@PathVariable Long commentId, @RequestBody NewsReply entity){
        return service.save(commentId, entity);
    }

    @DeleteMapping("/intercept/deleteById/{id}")
    public Map deleteById(@PathVariable Long id){
        map.clear();
        map.put("message", service.deleteById(id));
        return map;
    }

    @GetMapping("/findByCommentId/{commentId}")
    public Page<NewsReply> findByCommentId(@PathVariable Long commentId, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Pageable pageable = PageRequest.of(num, size);
        return service.findByCommentId(commentId, pageable);
    }
}
