package pers.xyj.modules.accountKeeper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;
import org.springframework.web.multipart.MultipartFile;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.AddRecordDto;
import pers.xyj.modules.accountKeeper.domain.dto.EditRecordDto;
import pers.xyj.modules.accountKeeper.domain.entity.Record;

import java.util.Date;
import java.util.List;

public interface RecordService extends IService<Record> {

    ResponseResult addRecord(AddRecordDto recordDto);

    ResponseResult deleteRecord(Long id);

    ResponseResult editRecord(EditRecordDto editRecordDto);

    ResponseResult getRecords(Long bookId, Integer tId, Date date, Integer pageNum, Integer pageSize);

    ResponseResult addRecordByPicture(MultipartFile img);

    ResponseResult addRecordList(List<AddRecordDto> recordDtos);

    ResponseResult getTopBookRecords(Integer tId, Date date, Integer pageNum, Integer pageSize);
}

