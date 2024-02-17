package pers.xyj.modules.auth.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVo {
    //菜单名
    private String menuName;
    //路由地址
    private String path;
}
