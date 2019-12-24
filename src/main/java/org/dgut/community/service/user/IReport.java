package org.dgut.community.service.user;

import org.dgut.community.entity.Report;

public interface IReport {
    Report findById(Long id);

    String deleteById(Long id);

    Report updateById(Long id);

    Report save(Report report);
}
