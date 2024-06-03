package pers.xyj.modules.accountKeeper.controller.accountKeeper;




import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.AddRecordDto;
import pers.xyj.modules.accountKeeper.domain.dto.AddTypeDto;
import pers.xyj.modules.accountKeeper.domain.dto.EditBookDto;
import pers.xyj.modules.accountKeeper.domain.entity.Type;
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
    @ApiOperation(value="获取类型页")
    @SystemLog(businessName = "获取类型页")
    @GetMapping("/page")
    public ResponseResult getTypesPage(Integer pageNum, Integer pageSize){
        return typeService.getTypesPage(pageNum, pageSize);
    }
    @ApiOperation(value = "删除类型")
    @SystemLog(businessName = "删除类型")
    @DeleteMapping("/{id}")
    public ResponseResult deleteType(@PathVariable("id") Integer id) {
        return typeService.deleteType(id);
    }

    @ApiOperation(value = "添加记录类型")
    @SystemLog(businessName = "添加记录类型")
    @PostMapping
    public ResponseResult addType(@RequestBody AddTypeDto typeDto){
        return typeService.addType(typeDto);
    }

    @ApiOperation(value = "编辑记录类型")
    @SystemLog(businessName = "编辑记录类型")
    @PutMapping
    public ResponseResult editType(@RequestBody Type type) {
        return typeService.editType(type);
    }

}