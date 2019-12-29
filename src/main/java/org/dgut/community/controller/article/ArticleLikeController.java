package org.dgut.community.controller.article;

import org.dgut.community.entity.ArticleLike;
import org.dgut.community.entity.FourmArticle;
import org.dgut.community.entity.User;
import org.dgut.community.resultenum.Result;
import org.dgut.community.service.article.impl.ArticleLikeServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/articleLike")
public class ArticleLikeController {

    private ArticleLikeServiceImpl service;

    public ArticleLikeController(ArticleLikeServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/intercept/save/{articleId}")
    public ResponseEntity<Result> save(@PathVariable Long articleId, @RequestBody ArticleLike like){
        return service.save(articleId, like);
    }

    @DeleteMapping("/intercept/deleteById/{articleId}")
    public ResponseEntity<?> deleteById(@PathVariable Long articleId, @RequestBody ArticleLike like){
        return service.deleteByArticleId(articleId, like);
    }

    @GetMapping("/findArticle/{userId}")
    public List<FourmArticle> findArticle(@PathVariable Long userId, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(num, size, sort);
        return service.findByUserId(userId, pageable);
    }

}
