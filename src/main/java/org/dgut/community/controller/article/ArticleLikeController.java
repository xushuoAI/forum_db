package org.dgut.community.controller.article;

import org.dgut.community.entity.ArticleLike;
import org.dgut.community.resultenum.Result;
import org.dgut.community.service.article.impl.ArticleLikeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
