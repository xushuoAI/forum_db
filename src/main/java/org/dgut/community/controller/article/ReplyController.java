package org.dgut.community.controller.article;

import org.dgut.community.entity.ArticleComment;
import org.dgut.community.entity.ArticleReply;
import org.dgut.community.resultenum.Result;
import org.dgut.community.service.article.impl.ReplyServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Result> save(@PathVariable Long commentId, @RequestBody ArticleReply entity){
        return service.save(commentId, entity);
    }

    @DeleteMapping("/intercept/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return service.deleteById(id);
    }

    @GetMapping("/findByCommentId/{commentId}")
    public Page<ArticleReply> findByCommentId(@PathVariable Long commentId, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(num, size, sort);
        return service.findByCommentId(commentId, pageable);
    }
}
