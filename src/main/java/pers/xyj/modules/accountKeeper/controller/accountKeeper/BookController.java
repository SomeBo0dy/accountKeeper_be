package pers.xyj.modules.accountKeeper.controller.accountKeeper;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import netscape.javascript.JSObject;
import org.apache.commons.math3.analysis.function.Add;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.AddBookDto;
import pers.xyj.modules.accountKeeper.domain.dto.EditBookDto;
import pers.xyj.modules.accountKeeper.domain.entity.Book;
import pers.xyj.modules.accountKeeper.service.BookService;
import pers.xyj.modules.common.annotation.SystemLog;
import pers.xyj.modules.common.constants.SystemConstants;

import java.util.Date;

@Api(value = "BookControllerApi", tags = {"账本操作接口"})
@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    private BookService bookService;

    @ApiOperation(value = "添加账本")
    @SystemLog(businessName = "添加账本")
    @PostMapping
    public ResponseResult addBook(@RequestBody AddBookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @ApiOperation(value = "删除账本")
    @SystemLog(businessName = "删除账本")
    @DeleteMapping("/{id}")
    public ResponseResult deleteBook(@PathVariable("id") Integer id) {
        return bookService.deleteBook(id);
    }

    @ApiOperation(value = "编辑账本")
    @SystemLog(businessName = "编辑账本")
    @PutMapping
    public ResponseResult editBook(@RequestBody EditBookDto book) {
        return bookService.editBook(book);
    }

    @ApiOperation(value = "获取账本")
    @SystemLog(businessName = "获取账本")
    @GetMapping
    public ResponseResult getBooks(Integer pageNum, Integer pageSize) {
        return bookService.getBooks(pageNum, pageSize);
    }

    @ApiOperation(value = "获取账本统计信息")
    @SystemLog(businessName = "获取账本统计信息")
    @GetMapping("/statistics/{bookId}")
    public ResponseResult getBookStatistics(@PathVariable("bookId") Integer bookId) {
        return bookService.getBookStatistics(bookId);
    }

    @ApiOperation(value = "导出账单")
    @SystemLog(businessName = "导出账单")
    @GetMapping("/output")
    public ResponseResult outputBook(Integer bookId, Date startTime, Date endTime) {
        return bookService.outputBook(bookId, startTime, endTime);
    }

    @ApiOperation(value = "搜索账单")
    @SystemLog(businessName = "搜索账单")
    @GetMapping("/search")
    public ResponseResult searchBookByName(String search, Integer pageNum, Integer pageSize) {
        return bookService.searchBookByName(search, pageNum, pageSize);
    }
}

