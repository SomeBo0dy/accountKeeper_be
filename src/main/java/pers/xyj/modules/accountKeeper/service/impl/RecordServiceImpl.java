package pers.xyj.modules.accountKeeper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.AddRecordDto;
import pers.xyj.modules.accountKeeper.domain.dto.EditRecordDto;
import pers.xyj.modules.accountKeeper.domain.entity.Book;
import pers.xyj.modules.accountKeeper.domain.entity.BookUser;
import pers.xyj.modules.accountKeeper.domain.vo.BookAndRecordVo;
import pers.xyj.modules.accountKeeper.domain.vo.PageVo;
import pers.xyj.modules.accountKeeper.domain.vo.RecordVo;
import pers.xyj.modules.accountKeeper.mapper.BookMapper;
import pers.xyj.modules.accountKeeper.mapper.BookUserMapper;
import pers.xyj.modules.accountKeeper.mapper.RecordMapper;
import pers.xyj.modules.accountKeeper.domain.entity.Record;
import pers.xyj.modules.accountKeeper.service.RecordService;
import org.springframework.stereotype.Service;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.common.utils.BeanCopyUtils;
import pers.xyj.modules.common.utils.SecurityUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private BookUserMapper bookUserMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public ResponseResult addRecord(AddRecordDto recordDto) {
        Record record = BeanCopyUtils.copeBean(recordDto, Record.class);
        record.setCreateDate(new Date(recordDto.getTimeInMillis()));
        int insert = recordMapper.insert(record);
        if (insert != 1) {
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteRecord(Long id) {
        int delete = recordMapper.deleteById(id);
        if (delete != 1) {
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult editRecord(EditRecordDto editRecordDto) {
        Record record = BeanCopyUtils.copeBean(editRecordDto, Record.class);
        int update = recordMapper.updateById(record);
        if (update != 1) {
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getRecords(Long bookId, Integer type, Date date, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Record> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(!Objects.isNull(bookId), Record::getBId, bookId);
        queryWrapper.eq(!Objects.isNull(type), Record::getTId, type);
        if (!Objects.isNull(date)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);
            queryWrapper
                    .ge(Record::getCreateDate, format.format(date))
                    .le(Record::getCreateDate, format.format(calendar.getTime()));
        }
        queryWrapper.orderByDesc(Record::getCreateDate);
        Page<Record> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        PageVo pageVo = new PageVo(page.getRecords(), page.getPages(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addRecordByPicture(MultipartFile img) {
        //TODO
        return null;
    }

    @Override
    public ResponseResult addRecordList(List<AddRecordDto> recordDtos) {
        List<Record> records = BeanCopyUtils.copyBeanList(recordDtos, Record.class);
        records.stream().forEach(record -> recordMapper.insert(record));
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getTopBookRecords(Integer tId, Date date, Integer pageNum, Integer pageSize) {
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<BookUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(BookUser::getPriority);
        lambdaQueryWrapper.eq(BookUser::getUId, userId);
        lambdaQueryWrapper.last("limit 1");
        BookUser bookUser = bookUserMapper.selectOne(lambdaQueryWrapper);
        Integer bookId = bookUser.getBId();
        Book book = bookMapper.selectById(bookId);
        LambdaQueryWrapper<Record> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Record::getBId, bookId);
        queryWrapper.eq(!Objects.isNull(tId), Record::getTId, tId);
        if (!Objects.isNull(date)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);
            queryWrapper
                    .ge(Record::getCreateDate, format.format(date))
                    .le(Record::getCreateDate, format.format(calendar.getTime()));
        }
        queryWrapper.orderByDesc(Record::getCreateDate);
        IPage<RecordVo> page = new Page<>(pageNum, pageSize);
        recordMapper.getTopBookRecords(page, queryWrapper);

        PageVo pageVo = new PageVo(page.getRecords(), page.getPages(), page.getTotal());
        BookAndRecordVo bookAndRecordVo = new BookAndRecordVo(book.getId(), book.getName(), book.getCreateBy(), book.getDescription(), pageVo);
        return ResponseResult.okResult(bookAndRecordVo);
    }
}

