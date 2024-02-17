package pers.xyj.modules.auth.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuAddDto {
    //菜单名
    private String menuName;
    //权限标识
    private String perms;
}
