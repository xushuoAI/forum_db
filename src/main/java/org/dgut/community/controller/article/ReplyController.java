package org.dgut.community.controller.article;

import org.dgut.community.entity.ArticleComment;
import org.dgut.community.entity.ArticleReply;
import org.dgut.community.service.article.impl.ReplyServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/articleReply")
public class ReplyController {
    private ReplyServiceImpl service;
    private Map<String, Object> map = new HashMap<String, Object>();

    public ReplyController(ReplyServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/intercept/save/{commentId}")
    public ArticleReply save(@PathVariable Long commentId, @RequestBody ArticleReply entity){
        return service.save(commentId, entity);
    }

    @DeleteMapping("/intercept/deleteById/{id}")
    public Map deleteById(@PathVariable Long id){
        map.clear();
        map.put("message", service.deleteById(id));
        return map;
    }

    @GetMapping("/findByCommentId/{commentId}")
    public Page<ArticleReply> findByCommentId(@PathVariable Long commentId, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Pageable pageable = PageRequest.of(num, size);
        return service.findByCommentId(commentId, pageable);
    }
}
