package org.dgut.community.service.user.impl;

import org.dgut.community.NotFoundException;
import org.dgut.community.entity.Report;
import org.dgut.community.repository.user.ReportRepository;
import org.dgut.community.service.user.IReport;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements IReport {
    private ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        return reportRepository.findById(id).map(report -> {
            reportRepository.delete(report);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new NotFoundException("未找到该举报信息"));
    }

    @Override
    public Report updateById(Long id) {
        return reportRepository.findById(id).map(report -> {
            report.setReprtManage(1);
            return reportRepository.save(report);
        }).orElseThrow(()-> new NotFoundException("未找到该举报信息"));
    }

    @Override
    public Report save(Report report) {
        return reportRepository.save(report);
    }
}
