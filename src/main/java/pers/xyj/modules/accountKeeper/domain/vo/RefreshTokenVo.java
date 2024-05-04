package pers.xyj.modules.accountKeeper.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenVo {
    private String newAccessToken;
    private String newRefreshToken;
}
