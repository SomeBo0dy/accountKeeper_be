package pers.xyj.modules.accountKeeper.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.BookPriorityDto;
import pers.xyj.modules.accountKeeper.mapper.BookUserMapper;
import pers.xyj.modules.accountKeeper.domain.entity.BookUser;
import pers.xyj.modules.accountKeeper.service.BookUserService;
import org.springframework.stereotype.Service;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.common.utils.SecurityUtils;

import java.util.ArrayList;


@Data
@Slf4j
@Service
public class BookUserServiceImpl extends ServiceImpl<BookUserMapper, BookUser> implements BookUserService {
    @Autowired
    private BookUserMapper bookUserMapper;

    @Override
    @Transactional
    public ResponseResult editBooksPriority(ArrayList<BookPriorityDto> priorityDto) {
        Long userId = SecurityUtils.getUserId();
//        LambdaUpdateWrapper<BookUser> updateWrapper = new LambdaUpdateWrapper<>();
//        updateWrapper.eq(BookUser::getUId, userId);
        for (BookPriorityDto item : priorityDto) {
            Long bookId = item.getId();
            Integer priority = item.getPriority();
//            updateWrapper.eq(BookUser::getBId,bookId);
//            updateWrapper.set(BookUser::getPriority,priority);
            int update = bookUserMapper.updateBooksPriority(userId, bookId, priority);
            if (update != 1){
                throw new SystemException(AppHttpCodeEnum.ERROR);
            }
        }
        return ResponseResult.okResult();
    }
}

