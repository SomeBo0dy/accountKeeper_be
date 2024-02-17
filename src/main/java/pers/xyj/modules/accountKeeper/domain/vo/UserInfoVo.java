package pers.xyj.modules.accountKeeper.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;

@Data
@Accessors(chain = true)
public class UserInfoVo {
    private Long id;
    private String nickName;
    private String avatar;
    private String sex;
    private String type;
    @Column(name = "type_name")
    private String typeName;
    //账号状态（0正常 1停用）
    private String state;
    private String qqNumber;

    //手机号
    private String phoneNumber;
    //个人简介
    private String introduction;
    private Long goodCount;
    private Long rejectCount;
}
