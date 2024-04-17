package pers.xyj.modules.accountKeeper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import pers.xyj.modules.accountKeeper.domain.entity.BookUser;
@Mapper
public interface BookUserMapper extends BaseMapper<BookUser> {


    @Update("UPDATE ak_book_user " +
            "SET priority = #{priority} " +
            "WHERE u_id = #{userId} AND b_id = #{bookId} ")
    int updateBooksPriority(@Param("userId") Long userId, @Param("bookId") Long bookId, @Param("priority") Integer priority);
}

