package org.dgut.community.repository.user;

import org.dgut.community.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Page<Report> findAll(Pageable pageable);
    Page<Report> findByReportReasonLike(String reason, Pageable pageable);
}
