package pers.xyj.modules.accountKeeper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.AddBookDto;
import pers.xyj.modules.accountKeeper.domain.dto.EditBookDto;
import pers.xyj.modules.accountKeeper.domain.entity.Book;

import java.util.Date;


public interface BookService extends IService<Book> {

    ResponseResult addBook(AddBookDto bookDto);

    ResponseResult deleteBook(Integer id);

    ResponseResult editBook(EditBookDto book);

    ResponseResult getBooks(Integer pageNum, Integer pageSize);

    ResponseResult shareBook();

    ResponseResult joinBook(String code);

    ResponseResult quitBook(Integer id);

    ResponseResult outputBook(Integer bookId, Date startTime, Date endTime);

    ResponseResult searchBookByName(String search, Integer pageNum, Integer pageSize);
}

