package pers.xyj.modules.accountKeeper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.xyj.modules.accountKeeper.domain.entity.Book;
import pers.xyj.modules.accountKeeper.domain.vo.BookStatisticsVo;
import pers.xyj.modules.accountKeeper.domain.vo.BookVo;
import pers.xyj.modules.accountKeeper.domain.vo.TypeNameAndCountVo;

import java.util.ArrayList;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    @Select("SELECT b.id, b.name, b.description, bu.priority, b.create_by, b.create_time " +
            "FROM ak_book b " +
            "JOIN ak_book_user bu ON b.id = bu.b_id " +
            "WHERE bu.u_id = #{uId} " +
            "ORDER BY bu.priority Desc, b.update_time DESC ")
    IPage<BookVo> getBooksByUserId(IPage<BookVo> page, @Param("uId") Long userId);
    @Select("SELECT b.id, b.name, b.description, b.create_by, b.create_time " +
            "FROM ak_book b " +
            "JOIN ak_book_user bu ON b.id = bu.b_id " +
            "WHERE bu.u_id = #{uId} AND b.name LIKE CONCAT('%',#{search},'%') " +
            "ORDER BY b.create_time Desc, b.update_time DESC ")
    IPage<Book> getBooksByUserIdLikeName(IPage<Book> page, @Param("uId") Long userId, @Param("search") String search);
    @Select("SELECT t.id as typeId, t.name as typeName, count(*) as count, sum(r.amount) as amount " +
            "FROM ak_type t " +
            "LEFT JOIN ak_record r ON t.id = r.t_id " +
            "WHERE r.b_id = #{bookId} " +
            "GROUP BY t.id")
    ArrayList<TypeNameAndCountVo> getBookStatistics(@Param("bookId")Integer bookId);

}

