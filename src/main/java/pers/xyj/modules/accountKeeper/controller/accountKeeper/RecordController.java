package pers.xyj.modules.accountKeeper.controller.accountKeeper;




import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.AddRecordDto;
import pers.xyj.modules.accountKeeper.domain.dto.EditRecordDto;
import pers.xyj.modules.accountKeeper.service.RecordService;
import pers.xyj.modules.common.annotation.SystemLog;

import java.util.Date;
import java.util.List;


@Api(value = "RecordControllerApi", tags = {"记录操作接口"})
@RestController
@RequestMapping("records")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @ApiOperation(value = "添加记录")
    @SystemLog(businessName = "添加记录")
    @PostMapping
    public ResponseResult addRecord(@RequestBody AddRecordDto recordDto){
        return recordService.addRecord(recordDto);
    }
    @ApiOperation(value = "批量添加记录")
    @SystemLog(businessName = "批量添加记录")
    @PostMapping("/list")
    public ResponseResult addRecordList(@RequestBody List<AddRecordDto> recordDtos){
        return recordService.addRecordList(recordDtos);
    }
    @ApiOperation(value = "删除记录")
    @SystemLog(businessName = "删除记录")
    @DeleteMapping("/{id}")
    public ResponseResult deleteRecord(@PathVariable("id") Long id){
        return recordService.deleteRecord(id);
    }

    @ApiOperation(value = "编辑记录")
    @SystemLog(businessName = "编辑记录")
    @PutMapping
    public ResponseResult editRecord(@RequestBody EditRecordDto editRecordDto){
        return recordService.editRecord(editRecordDto);
    }
    @ApiOperation(value="获取记录")
    @SystemLog(businessName = "获取记录")
    @GetMapping
    public ResponseResult getRecords(Long bookId, Integer type, Date date, Integer pageNum, Integer pageSize){
        return recordService.getRecords(bookId, type, date, pageNum, pageSize);
    }
    @ApiOperation(value="获取最高优先级账本信息以及记录")
    @SystemLog(businessName = "获取最高优先级账本信息以及记录")
    @GetMapping("/top")
    public ResponseResult getTopBookRecords(Integer type, Date date, Integer pageNum, Integer pageSize){
        return recordService.getTopBookRecords(type, date, pageNum, pageSize);
    }

    @ApiOperation(value = "上传图片实现智能记录")
    @SystemLog(businessName = "上传图片实现智能记录")
    @PostMapping("/img")
    public ResponseResult addRecordByPicture(MultipartFile img){
        return recordService.addRecordByPicture(img);
    }

}

