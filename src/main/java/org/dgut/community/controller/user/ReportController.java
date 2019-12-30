package org.dgut.community.controller.user;

import org.dgut.community.entity.Report;
import org.dgut.community.entity.User;
import org.dgut.community.resultenum.Result;
import org.dgut.community.service.user.impl.ReportServiceImpl;
import org.dgut.community.util.ResultUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/userReport")
public class ReportController {
    private ReportServiceImpl service;

    public ReportController(ReportServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/intercept/save")
    public ResponseEntity<Result> save(@RequestBody Report entity, HttpSession session){
        User user = (User) session.getAttribute("user");
        entity.setReporterId(user.getUserId());
        Report report = service.save(entity);
        return ResponseEntity.ok(ResultUtil.success(report));
    }

    @PutMapping("/intercept/updateById/{reportId}")
    public ResponseEntity<Result> updateById(@PathVariable Long reportId){
        Report report = service.updateById(reportId);
        return ResponseEntity.ok(ResultUtil.success(report));
    }

    @DeleteMapping("/intercept/deleteById/{reportId}")
    public ResponseEntity<?> deleteById(@PathVariable Long reportId){
        return service.deleteById(reportId);
    }

    @GetMapping("/intercept/findAll")
    public Page<Report> findAll(@RequestParam(defaultValue = "0") int num, @RequestParam(defaultValue = "15") int size){
        Sort sort = Sort.by(Sort.Direction.DESC, "reportId");
        Pageable pageable = PageRequest.of(num, size, sort);
        return service.findAll(pageable);
    }
}
