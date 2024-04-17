package pers.xyj.modules.accountKeeper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.BookPriorityDto;
import pers.xyj.modules.accountKeeper.domain.entity.BookUser;

import java.util.ArrayList;


public interface BookUserService extends IService<BookUser> {

    ResponseResult editBooksPriority(ArrayList<BookPriorityDto> priorityDto);
}

