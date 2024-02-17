package pers.xyj.modules.accountKeeper.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PasswordUserDto {
    @ApiModelProperty(value = "账号")
    private String account;
    //密码
    @ApiModelProperty(value = "密码")
    private String password;
    //手机
    @ApiModelProperty(value = "手机")
    private String phoneNumber;
    //    @ApiModelProperty(value = "验证码")
//    private String code;
    @ApiModelProperty(value = "QQ")
    private String qqNumber;
    //用户类型
    @ApiModelProperty(value = "用户类型：0代表只读用户，2代表最高管理员")
    private String type;
}
