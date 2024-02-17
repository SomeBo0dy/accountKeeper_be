package pers.xyj.modules.auth.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (RoleMenu)表实体类
 *
 * @author makejava
 * @since 2023-05-02 14:14:23
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role_menu")
public class RoleMenu extends Model<RoleMenu> {
    //角色ID
    private Long roleId;
    //菜单id
    private Long menuId;

}
