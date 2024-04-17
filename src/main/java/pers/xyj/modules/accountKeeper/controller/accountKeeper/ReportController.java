package pers.xyj.modules.accountKeeper.controller.accountKeeper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.PhoneLoginDto;
import pers.xyj.modules.accountKeeper.service.ReportService;
import pers.xyj.modules.common.annotation.SystemLog;

@Api(value = "ReportControllerApi",tags={"报告操作接口"})
@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @ApiOperation(value = "查看往期报告")
    @SystemLog(businessName = "查看往期报告")
    @GetMapping
    public ResponseResult getReports() {
        return reportService.getReports();
    }
}
