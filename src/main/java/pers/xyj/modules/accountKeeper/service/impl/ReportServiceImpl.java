package pers.xyj.modules.accountKeeper.service.impl;

import lombok.Data;
import org.springframework.stereotype.Service;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.service.ReportService;
@Service
@Data
public class ReportServiceImpl implements ReportService {
    @Override
    public ResponseResult getReports() {
        //TODO
        return null;
    }
}
