package org.dgut.community.controller.user;

import org.dgut.community.entity.Report;
import org.dgut.community.service.user.impl.ReportServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userReport")
public class ReportController {
    private ReportServiceImpl service;

    public ReportController(ReportServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/intercept/save")
    public Report save(@RequestBody Report entity){
        return service.save(entity);
    }
}
