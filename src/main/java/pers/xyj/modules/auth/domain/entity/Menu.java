package pers.xyj.modules.auth.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 菜单表(Menu)表实体类
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu")
public class Menu {
    @TableId(type = IdType.AUTO)
    private Long id;

    //菜单名
    private String menuName;
    //路由地址
    private String path;
    //组件路径
    private String component;
    //菜单状态（0显示 1隐藏）
    private String visible;
    //菜单状态（0正常 1停用）
    private String state;
    //权限标识
    private String perms;
    //菜单图标
    private String icon;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //备注
    private String remark;

}

