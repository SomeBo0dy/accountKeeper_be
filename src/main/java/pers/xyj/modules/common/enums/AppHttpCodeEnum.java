package pers.xyj.modules.common.enums;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    ERROR(404, "操作失败"),

    // 登录
    NEED_LOGIN(401, "需要登录后操作"),
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
    DEVICE_NAME_NOT_NULL(400121, "设备名不得为空"),
    DEVICE_ID_NOT_NULL(400122, "设备ID不得为空" ),
    DEVICE_STATE_NOT_NULL(400123, "设备状态不得为空" ),
    DEVICELIST_ID_NOT_NULL(400124, "设备列表ID不得为空" ),
    ROOM_ID_NOT_NULL(400125, "机房ID不得为空"),
    CPU_USAGE_NOT_NULL(400126, "cpu usage 不得为空"),
    TEMPERATURE_NOT_NULL(400127, "温度不得为空"),
    READ_RATE_NOT_NULL(400128, "readRate不得为空"),
    WRITE_RATE_NOT_NULL(400129, "writeRate不得为空"),
    UPLOAD_RATE_NOT_NULL(400129, "uploadRate不得为空"),
    DOWNLOAD_RATE_NOT_NULL(400129, "downloadRate不得为空"),
    DEVICESTATIS_ID_NOT_NULL(400130, "deviceStatisId不得为空"),
    FLOOR_ID_NOT_NULL(400131, "楼层id不得为空"),
    ENTITY_ID_NOT_NULL(400132, "entityId不得为空"),
    LOG_ID_NOT_NULL(400133,"日志ID不得为空"),
    SENSOR_ID_NOT_NULL(400134,"传感器ID不得为空"),
    LEVEL_NOT_NULL(400135,"等级不得为空"),
    SENSORLIST_ID_NOT_NULL(400136,"传感器列表ID不得为空"),
    SENSOR_NAME_NOT_NULL(400137,"传感器名不得为空"),
    GROUP_ID_NOT_NULL(400138,"机房组id不得为空"),
    GROUP_NAME_NOT_NULL(400139,"机房组名字不得为空"),
    GROUP_DES_NOT_NULL(400140,"机房组描述不得为空"),
    FLOOR_NAME_NOT_NULL(400141,"楼层名字不得为空"),
    FLOOR_DES_NOT_NULL(400142,"楼层描述不得为空"),
    DEVICEGROUP_ID_NOT_NULL(400143,"设备组id不得为空"),
    DEVICEGROUP_NAME_NOT_NULL(400144,"设备组名字不得为空"),
    DEVICEGROUP_DES_NOT_NULL(400145,"设备组描述不得为空"),
    DEVICEGROUPLIST_ID_NOT_NULL(400146,"设备组LISTid不得为空"),
    ROOM_NAME_NOT_NULL(400147, "机房名不得为空"),
    HUMIDITY_NOT_NULL(400148, "湿度不为空"),
    ROOMSTATISTICS_ID_NOT_NULL(400149, "机房统计信息id不得为空"),
    ROOMLIST_ID_NOT_NULL(400150, "机房列表id不得为空"),
    LOG_PICTURE_ID_NOT_NULL(400151,"机房图片id不得为空"),
    PIC_ADDR_NOT_NULL(400152,"图片地址不得为空"),
    DATE_NOT_NULL(400151, "日期参数不得为空"),
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
