package org.dgut.community.service.user;

import org.dgut.community.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IReport {
    Page<Report> findAll(Pageable pageable);

    Page<Report> findByReportReasonLike(String reason, Pageable pageable);

    ResponseEntity<?> deleteById(Long id);

    Report updateById(Long id);

    Report save(Report report);
}
