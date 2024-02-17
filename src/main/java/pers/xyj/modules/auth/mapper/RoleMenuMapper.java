package pers.xyj.modules.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.xyj.modules.auth.domain.entity.RoleMenu;

/**
 * (RoleMenu)表数据库访问层
 *
 * @author xyj
 * @since 2023-05-02 14:14:23
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

}

