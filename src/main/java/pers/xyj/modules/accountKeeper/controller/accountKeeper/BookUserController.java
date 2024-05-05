package pers.xyj.modules.accountKeeper.controller.accountKeeper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.BookPriorityDto;
import pers.xyj.modules.accountKeeper.domain.entity.Book;
import pers.xyj.modules.accountKeeper.service.BookUserService;
import pers.xyj.modules.common.annotation.SystemLog;

import java.util.ArrayList;

@Api(value = "BookUserControllerApi", tags = {"账本用户操作接口"})
@RestController
@RequestMapping("bookUsers")
public class BookUserController {

    @Autowired
    private BookUserService bookUserService;

    @ApiOperation(value = "编辑账本优先级")
    @SystemLog(businessName = "编辑账本优先级")
    @PutMapping("/priority")
    public ResponseResult editBooksPriority(@RequestBody ArrayList<BookPriorityDto> priorityDto) {
        return bookUserService.editBooksPriority(priorityDto);
    }
}
