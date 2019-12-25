package org.dgut.community.controller.news;

import org.dgut.community.entity.FourmArticle;
import org.dgut.community.entity.News;
import org.dgut.community.service.news.impl.NewsServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/news")
public class NewsController {
    private NewsServiceImpl service;
    private Map<String, Object> map = new HashMap<String, Object>();

    public NewsController(NewsServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/intercept/save/{id}")
    public News save(@RequestBody News entity, @PathVariable Long id){
        return service.save(entity, id);
    }

    @DeleteMapping("/intercept/deleteById/{id}")
    public Map deleteById(@PathVariable Long id){
        map.clear();
        map.put("message", service.deleteById(id));
        return map;
    }

    @PutMapping("/intercept/updateById/{id}")
    public News updateById(@PathVariable Long id, @RequestBody News news){
        return service.updateById(id, news);
    }

    @PutMapping("/updateViewTimes/{id}")
    public News updateViewTimes(@PathVariable Long id){
        return service.updateViewTimes(id);
    }

    @PutMapping("/intercept/updateLike/{id}/{num}")
    public News updateLike(@PathVariable Long id, @PathVariable int num){
        return service.updateLike(id, num);
    }

    @PutMapping("/intercept/updateCollect/{id}/{num}")
    public News updateCollect(@PathVariable Long id, @PathVariable int num){
        return service.updateCollect(id, num);
    }

    @GetMapping("/findByTitleOrContent")
    public Page<News> findByTitleOrContent(@RequestBody News news, @RequestParam int num, @RequestParam(defaultValue = "15") int size){
        Pageable pageable = PageRequest.of(num, size);
        return service.findByNewsContentLikeOrNewsTitleLike(news.getNewsTitle(), pageable);
    }

    @GetMapping("/findAll")
    public Page<News> findAll(@RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Pageable pageable = PageRequest.of(num, size);
        return service.findAll(pageable);
    }

    @GetMapping("/findByUserId/{userId}")
    public Page<News> findByUserId(@PathVariable Long userId, @RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Pageable pageable = PageRequest.of(num, size);
        return service.findByUser_userId(userId, pageable);
    }
}
