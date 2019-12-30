package org.dgut.community.service.user.impl;

import org.dgut.community.NotFoundException;
import org.dgut.community.entity.Report;
import org.dgut.community.repository.user.ReportRepository;
import org.dgut.community.resultenum.ResultEnum;
import org.dgut.community.service.user.IReport;
import org.dgut.community.util.ResultUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements IReport {
    private ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Page<Report> findAll(Pageable pageable) {
        return reportRepository.findAll(pageable);
    }

    @Override
    public Page<Report> findByReportReasonLike(String reason, Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        return reportRepository.findById(id).map(report -> {
            reportRepository.delete(report);
            return ResponseEntity.ok(ResultUtil.success());
        }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public Report updateById(Long id) {
        return reportRepository.findById(id).map(report -> {
            report.setReportManage(1);
            return reportRepository.save(report);
        }).orElseThrow(()-> new NotFoundException(ResultEnum.ID_NOT_EXIST));
    }

    @Override
    public Report save(Report report) {
        return reportRepository.save(report);
    }
}
