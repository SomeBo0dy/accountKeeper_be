package pers.xyj.modules.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.xyj.modules.auth.domain.entity.Role;
import pers.xyj.modules.auth.domain.entity.UserRole;


/**
 * 角色表(Role)表数据库访问层
 *
 * @author xyj
 * @since 2022-07-29 09:54:51
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Select(" SELECT ur.* " +
            " FROM sys_user_role ur " +
            " LEFT JOIN sys_role r ON r.id = ur.role_id " +
            " LEFT JOIN sys_user u ON u.id = ur.user_id " +
            " WHERE r.name = #{roleName} AND u.id = #{userId} ")
    UserRole getByRoleNameAndUserId(@Param("userId") Long userId, @Param("roleName")String roleName);
//    @Select(" SELECT m.menu_name, m.path " +
//            " FROM sys_menu m " +
//            " LEFT JOIN sys_role_menu rm ON rm.menu_id = m.id " +
//            " LEFT JOIN sys_role r ON r.id = rm.role_id " +
//            " WHERE r.id = #{roleId}")
//    List<MenuVo> getMenuList(@Param("roleId") Long roleId);
}

