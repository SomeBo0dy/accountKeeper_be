package pers.xyj.modules.common.enums;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    ERROR(404, "操作失败"),

    NEED_LOGIN(401001, "需要登录后操作"),
    NEED_REFRESH_TOKEN(401002, "token过期，请刷新token"),
    REFRESH_EXPIRED_NEED_LOGIN(401003, "refreshToken过期，需要登录后操作"),
    // 登录

    NO_OPERATOR_AUTH(403, "无权限操作"),
    ACCOUNT_NOT_NULL(400100, "账号不得为空"),
    CODE_NOT_NULL(400102, "验证码不得为空"),
    PASSWORD_NOT_NULL(400103, "密码不得为空"),
    TYPE_NOT_NULL(400104, "类型不得为空"),
    PAGE_NUM_NOT_NULL(400105, "页码不得为空"),
    PAGE_SIZE_NOT_NULL(400106, "单页数据量不得为空"),
    USER_ID_NOT_NULL(400107, "用户ID不得为空"),
    TEXT_NOT_NULL(400108,"文件url不得为空" ),
    TIME_ORDER_NOT_NULL(400110,"时间顺序不得为空" ),
    STATE_TYPE_NOT_NULL(400112,"状态参数不得为空" ),
    CONTENT_NOT_NULL(400118,"内容不得为空" ),
    TIME_NOT_NULL(400119,"时间不得为空" ),
    PHONE_NUMBER_NOT_NULL(400120, "手机号不得为空"),

    SYSTEM_ERROR(500, "出现错误"),
    FILE_TYPE_ERROR(500100, "文件格式错误"),
    LOGIN_ERROR(500101, "用户名或密码错误"),
    CODE_OVERDUE(500102, "验证码已过期"),
    CODE_INCORRECT(500103, "验证码错误"),
    STATE_TYPE_ERROR(500110, "状态参数有误（0正常 1停用）"),

    PASSWORD_LOGIN_FAIL(500106, "账号密码登录失败"),
    ACCOUNT_EXIST(500107, "账号已存在"),
    PHONE_BIND(500108,"该手机号已被绑定"),

    TIME_ORDER_ERROR(500115,"时间排序参数错误" ),
    TYPE_ERROR(500117,"类型参数错误" ),
    PHONE_LOGIN_FAIL(500118,"手机验证码登录失败" ),
    NOT_BOOK_OWNER(500119,"操作失败，不是账本的创建者无法删除账本" ),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    USERNAME_NOT_NULL(506,"用户名不得为空" ),
    NICKNAME_NOT_NULL(507,"昵称不得为空" ),
    EMAIL_NOT_NULL(509,"邮箱不得为空" ),
    GOOD_TITLE_NOT_NULL(510,"商品标题不得为空" ),
    GOOD_NAME_NOT_NULL(511,"商品名称不得为空" ),
    GOOD_CATE_NOT_NULL(512,"商品类型不得为空" ),
    GOOD_PIC_URL_NOT_NULL(513,"商品缩略图不得为空" ),
    GOOD_PRICE_NOT_NULL(514,"商品价格不得为空" ),
    GOOD_NOT_EXIST(517,"该商品不存在" ),
    ORDER_ERROR(518,"订单异常" ),
    ORDER_ERROR_DEL(519,"订单重复" ),
    ORDER_ERROR_NULL(520,"订单不存在" ),
    ORDER_ERROR_GOOD_NOT_EXIST(521,"订单异常，商品不存在" ),
    ORDER_ERROR_WRONG_OWENER(522,"订单异常,卖家错误" ),
    ORDER_ERROR_DONE(523,"该订单已完成" );
    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
