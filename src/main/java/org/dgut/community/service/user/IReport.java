package org.dgut.community.service.user;

import org.dgut.community.entity.Report;
import org.springframework.http.ResponseEntity;

public interface IReport {
    Report findById(Long id);

    ResponseEntity<?> deleteById(Long id);

    Report updateById(Long id);

    Report save(Report report);
}
