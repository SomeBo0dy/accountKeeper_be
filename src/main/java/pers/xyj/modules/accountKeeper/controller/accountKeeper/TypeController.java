package pers.xyj.modules.accountKeeper.controller.accountKeeper;




import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.service.TypeService;
import pers.xyj.modules.common.annotation.SystemLog;


/**
 * (Type)表控制层
 *
 * @author makejava
 * @since 2024-04-16 13:02:29
 */
@Api(value = "TypeControllerApi",tags={"种类操作接口"})
@RestController
@RequestMapping("types")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @ApiOperation(value="获取类型")
    @SystemLog(businessName = "获取类型")
    @GetMapping
    public ResponseResult getTypes(){
        return typeService.getTypes();
    }
}