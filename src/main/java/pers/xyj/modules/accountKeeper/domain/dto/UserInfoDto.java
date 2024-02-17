package pers.xyj.modules.accountKeeper.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfoDto {

    //头像
    @ApiModelProperty(value = "用户头像url")
    private String avatar;
    //昵称
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    //用户性别
    @ApiModelProperty(value = "性别")
    private String sex;


    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    @ApiModelProperty(value = "QQ号")
    private String qqNumber;
    //个人简介
    @ApiModelProperty(value = "用户个人简介")
    private String introduction;

}
