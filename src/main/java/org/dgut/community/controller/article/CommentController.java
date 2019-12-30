package org.dgut.community.controller.article;

import org.dgut.community.entity.ArticleComment;
import org.dgut.community.entity.User;
import org.dgut.community.resultenum.Result;
import org.dgut.community.service.article.impl.CommentServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public ResponseEntity<Result> save(@PathVariable Long articleId, @RequestBody ArticleComment entity, HttpSession session){
        User user = (User) session.getAttribute("user");
        entity.setUserId(user.getUserId());
        return service.save(articleId, entity);
    }

    @DeleteMapping("/intercept/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return service.deleteById(id);
    }

    @PutMapping("/intercept/updateLike/{id}/{num}")
    public ResponseEntity<Result> updateLike(@PathVariable Long id, @PathVariable int num){
        return service.updateLike(id, num);
    }

    @PutMapping("/intercept/updateTop/{id}/{num}")
    public ResponseEntity<Result> updateTop(@PathVariable Long id, @PathVariable int num){
        return service.updateTop(id, num);
    }

    @GetMapping("/findByArticleId/{articleId}")
    public Page<ArticleComment> findByArticleId(@PathVariable Long articleId, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Sort sort = Sort.by(Sort.Direction.DESC, "commentId");
        Pageable pageable = PageRequest.of(num, size, sort);
        return service.findByArticleId(articleId, pageable);
    }
}
