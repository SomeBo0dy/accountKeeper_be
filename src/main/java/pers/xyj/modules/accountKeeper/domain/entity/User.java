package pers.xyj.modules.accountKeeper.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户表(User)表实体类
 *
 * @author xyj
 * @since 2022-03-26 14:59:58
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User {
    //主键@TableId
    @TableId(type = IdType.AUTO)
    private Long id;
    //昵称
    private String nickName;
    //密码
    private String password;
    //用户类型：0代表普通用户，1代表管理员
    private String type;
    //账号状态（0正常 1停用）
    private String state;
    //手机号
    private String phoneNumber;
    //用户性别
    private String sex;
    //头像
    private String avatar;
    //个人简介
    private String introduction;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}

