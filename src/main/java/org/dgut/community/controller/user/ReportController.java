package org.dgut.community.controller.user;

import org.dgut.community.entity.Report;
import org.dgut.community.resultenum.Result;
import org.dgut.community.service.user.impl.ReportServiceImpl;
import org.dgut.community.util.ResultUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userReport")
public class ReportController {
    private ReportServiceImpl service;

    public ReportController(ReportServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/intercept/save")
    public ResponseEntity<Result> save(@RequestBody Report entity){
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
}
