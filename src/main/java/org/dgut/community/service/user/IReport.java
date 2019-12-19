package org.dgut.community.service.user;

import org.dgut.community.entity.Report;

public interface IReport {
    Report findById(Long id);

    Report deleteById(Long id);

    Report updateById(Long id);

    Report save();
}
