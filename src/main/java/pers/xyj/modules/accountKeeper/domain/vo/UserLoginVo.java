package pers.xyj.modules.accountKeeper.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVo {
    private String accessToken;
    private String refreshToken;
    private UserInfoVo userInfoVo;
//    private List<MenuVo> authVo;
}
