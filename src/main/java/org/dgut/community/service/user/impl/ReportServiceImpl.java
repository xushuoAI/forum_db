package org.dgut.community.service.user.impl;

import org.dgut.community.entity.Report;
import org.dgut.community.repository.user.ReportRepository;
import org.dgut.community.service.user.IReport;
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
    public Report deleteById(Long id) {
        return null;
    }

    @Override
    public Report updateById(Long id) {
        return null;
    }

    @Override
    public Report save() {
        return null;
    }
}
