package pers.xyj.modules.accountKeeper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.AddBookDto;
import pers.xyj.modules.accountKeeper.domain.dto.EditBookDto;
import pers.xyj.modules.accountKeeper.domain.entity.BookUser;
import pers.xyj.modules.accountKeeper.domain.vo.BookStatisticsVo;
import pers.xyj.modules.accountKeeper.domain.vo.BookVo;
import pers.xyj.modules.accountKeeper.domain.vo.PageVo;
import pers.xyj.modules.accountKeeper.domain.vo.TypeNameAndCountVo;
import pers.xyj.modules.accountKeeper.mapper.BookMapper;
import pers.xyj.modules.accountKeeper.domain.entity.Book;
import pers.xyj.modules.accountKeeper.mapper.BookUserMapper;
import pers.xyj.modules.accountKeeper.mapper.RecordMapper;
import pers.xyj.modules.accountKeeper.service.BookService;
import org.springframework.stereotype.Service;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.common.utils.BeanCopyUtils;
import pers.xyj.modules.common.utils.SecurityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookUserMapper bookUserMapper;

    @Autowired
    private RecordMapper recordMapper;
    @Override
    @Transactional
    public ResponseResult addBook(AddBookDto bookDto) {
        Book book = BeanCopyUtils.copeBean(bookDto, Book.class);
        int insert = bookMapper.insert(book);
        if (insert != 1){
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        Long userId = SecurityUtils.getUserId();
        BookUser bookUser = new BookUser(userId, book.getId(), bookDto.getPriority());
        int insertBookUser = bookUserMapper.insert(bookUser);
        if (insertBookUser != 1){
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteBook(Integer id) {
        Book book = bookMapper.selectById(id);
        Long userId = SecurityUtils.getUserId();
        if (!userId.equals(book.getCreateBy())){
            throw new SystemException(AppHttpCodeEnum.NOT_BOOK_OWNER);
        }
        int delete = bookMapper.deleteById(id);
        if (delete != 1){
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult editBook(EditBookDto bookDto) {
        Book book = BeanCopyUtils.copeBean(bookDto, Book.class);
        int update = bookMapper.updateById(book);
        if (update != 1){
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getBooks(Integer pageNum, Integer pageSize) {
        Long userId = SecurityUtils.getUserId();
        IPage<BookVo> page = new Page<>(pageNum, pageSize);
        bookMapper.getBooksByUserId(page, userId);
        List<BookVo> bookVos = page.getRecords();
        for (BookVo item : bookVos) {
            Integer id = item.getId();
            LambdaQueryWrapper<BookUser> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(BookUser::getBId,id);
            Integer memberCount = bookUserMapper.selectCount(queryWrapper);
            Double incomeAmount = recordMapper.getIncomeAmountByBookId(id);
            Double outcomeAmount = recordMapper.getOutcomeAmountByBookId(id);
            item.setIncomeAmount(incomeAmount);
            item.setOutcomeAmount(outcomeAmount);
            item.setMemberCount(memberCount);
        }
        PageVo pageVo = new PageVo(bookVos, page.getPages(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult shareBook() {
        //TODO
        return null;
    }

    @Override
    public ResponseResult joinBook(String code) {
        //TODO
        return null;
    }

    @Override
    public ResponseResult quitBook(Integer id) {
        //TODO
        return null;
    }

    @Override
    public ResponseResult outputBook(Integer bookId, Date startTime, Date endTime) {
        //TODO
        return null;
    }

    @Override
    public ResponseResult searchBookByName(String search, Integer pageNum, Integer pageSize) {
        Long userId = SecurityUtils.getUserId();
        IPage<Book> page = new Page<>(pageNum, pageSize);
        bookMapper.getBooksByUserIdLikeName(page, userId, search);
        PageVo pageVo = new PageVo(page.getRecords(), page.getPages(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getBookStatistics(Integer bookId) {
        ArrayList<TypeNameAndCountVo> statistics =  bookMapper.getBookStatistics(bookId);
        int incomeCount = 0;
        int outcomeCount = 0;
        Double incomeAmountSum = 0.0;
        Double outcomeAmountSum = 0.0;
        ArrayList<TypeNameAndCountVo> incomeStatistics = new ArrayList<>();
        ArrayList<TypeNameAndCountVo> outcomeStatistics = new ArrayList<>();
        for (TypeNameAndCountVo item : statistics) {
            if (item.getIsIncome() == 1){
                incomeCount += item.getCount();
                incomeAmountSum += item.getAmount();
                incomeStatistics.add(item);
            }else {
                outcomeCount += item.getCount();
                outcomeAmountSum += item.getAmount();
                outcomeStatistics.add(item);
            }
        }
        BookStatisticsVo bookStatisticsVo = new BookStatisticsVo(incomeCount, outcomeCount, incomeAmountSum, outcomeAmountSum, incomeStatistics, outcomeStatistics);
        return ResponseResult.okResult(bookStatisticsVo);
    }
}

