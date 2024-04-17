package pers.xyj.modules.accountKeeper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.xyj.modules.accountKeeper.domain.entity.Type;

/**
 * (Type)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-16 13:02:29
 */
@Mapper
public interface TypeMapper extends BaseMapper<Type> {

}

