package pers.xyj.modules.accountKeeper.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.xyj.modules.accountKeeper.domain.entity.User;
import pers.xyj.modules.accountKeeper.domain.vo.UserInfoVo;

import java.util.List;


/**
 * 用户表(User)表数据库访问层
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select(" SELECT u.id, u.nick_name, u.avatar, u.sex, u.type, r.name type_name, u.state, u.phone_number, u.introduction ,u.good_count, reject_count" +
            " FROM sys_user u " +
            " LEFT JOIN sys_user_role ur ON ur.user_id = u.id " +
            " LEFT JOIN sys_role r ON r.id = ur.role_id " +
            " ${ew.customSqlSegment} ")
    IPage<UserInfoVo> getUserInfoByPage(IPage<UserInfoVo> page, @Param(Constants.WRAPPER) QueryWrapper<UserInfoVo> queryWrapper);

    @Select(" SELECT u.phone_number " +
            " FROM sys_user u " +
            " WHERE u.type = '2' ")
    List<String> getAdminPhone();
}

