package org.dgut.community.controller.article;

import com.alibaba.fastjson.JSONObject;
import org.dgut.community.entity.FourmArticle;
import org.dgut.community.entity.User;
import org.dgut.community.resultenum.Result;
import org.dgut.community.resultenum.ResultEnum;
import org.dgut.community.service.article.impl.FourmServiceImpl;
import org.dgut.community.util.HttpClient;
import org.dgut.community.util.ResultUtil;
import org.dgut.community.util.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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
    public ResponseEntity save(@RequestBody FourmArticle entity, @PathVariable Long id, HttpSession session){
        User user = (User)session.getAttribute("user");
        JSONObject BaiDuC=JSONObject.parseObject(HttpClient.doPost(entity.getArticleContent()));

        String baiduC= (String) BaiDuC.get("conclusion");

        if (baiduC.equals("合规")){
            return service.save(entity, user.getUserId());
        }else{
            return ResponseEntity.ok(ResultUtil.error(9011,"帖子内容存在违规，包含低俗色情"));
        }

    }

    @GetMapping("/findAll")
    public Page<FourmArticle> findAll(HttpSession session,
                                      @RequestParam(defaultValue = "0") int num,
                                      @RequestParam(defaultValue = "15") int size){
        User user = (User)session.getAttribute("user");
        Sort sort = Sort.by(Sort.Direction.DESC, "articleId");
        Pageable pageable = PageRequest.of(num, size, sort);
        if (user != null){
            return service.findAll(user.getUserId(), pageable);
        }else {
            Long userId = Long.parseLong("0");
            return service.findAll(userId, pageable);
        }
    }

    @GetMapping("/findByContent")
    public Page<FourmArticle> findByContent(HttpSession session,
                                            @RequestBody FourmArticle fourmArticle,
                                            @RequestParam(defaultValue = "0") int num,
                                            @RequestParam(defaultValue = "15") int size){
        User user = (User)session.getAttribute("user");
        Sort sort = Sort.by(Sort.Direction.DESC, "articleId");
        Pageable pageable = PageRequest.of(num, size, sort);
        if (user != null){
            return service.findByArticleContentLike(user.getUserId(), fourmArticle.getArticleContent(), pageable);
        }else {
            Long userId = Long.parseLong("0");
            return service.findByArticleContentLike(userId, fourmArticle.getArticleContent(), pageable);
        }
    }

    @GetMapping("/findByContent2")
    public Page<FourmArticle> findByContent2(HttpSession session,
                                             FourmArticle fourmArticle,
                                            @RequestParam(defaultValue = "0") int num,
                                            @RequestParam(defaultValue = "15") int size){
        User user = (User)session.getAttribute("user");
        Sort sort = Sort.by(Sort.Direction.DESC, "articleId");
        Pageable pageable = PageRequest.of(num, size, sort);
        if (user != null){
            return service.findByArticleContentLike(user.getUserId(), fourmArticle.getArticleContent(), pageable);
        }else {
            Long userId = Long.parseLong("0");
            return service.findByArticleContentLike(userId, fourmArticle.getArticleContent(), pageable);
        }
    }

    @GetMapping("/findByArticleId/{articleId}")
    public ResponseEntity<Result> findByArticleId(@PathVariable Long articleId, @RequestParam(defaultValue = "0") Long myId){
        return ResponseEntity.ok(ResultUtil.success(service.findByArticleId(articleId, myId)));
    }

    @GetMapping("/findByUserId/{userId}")
    public Page<FourmArticle> findByUserId(@PathVariable Long userId, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Sort sort = Sort.by(Sort.Direction.DESC, "articleId");
        Pageable pageable = PageRequest.of(num, size, sort);
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
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return service.deleteById(id);
    }
}
