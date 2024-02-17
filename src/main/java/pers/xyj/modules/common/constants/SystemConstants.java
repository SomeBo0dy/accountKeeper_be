package pers.xyj.modules.common.constants;

import java.util.HashMap;
import java.util.Map;

public class SystemConstants
{
    //商品尚未审核通过
    public static final String GOOD_STATUS_NOT_APPROVED = "1";
    //商品审核通过
    public static final String GOOD_STATUS_APPROVED = "0";
    //商品未出售
    public static final int GOOD_NOT_SOLD = 0;
    //商品已出售
    public static final int GOOD_SOLD = 1;
    //被删除
    public static final int GOOD_DEF = 1;

    //发送拒绝邮件
    public static final String GOOD_REJECTED_MESSAGE = "  很抱歉，您提交的商品不符合平台要求";
    //发送通过邮件
    public static final String GOOD_APPROVED_MESSAGE = "  您提交的商品已通过平台审核";
    public static final int ORDER_DONE = 1;
    public static final int ORDER_UNDO = 0;
    public static final String STATUS_NORMAL = "0";
    //是普通用户
    public static final String IS_USER = "0";
    //是管理员
    public static final String IS_ADMIN = "1";
    //降序
    public static final String DESC = "1";
    //升序
    public static final String ASC = "0";

    public static final String STATUS_BLOCK = "1";

    public static final Map<String, String> TYPE_MAP = new HashMap<>(); //Map.of(IS_USER, "用户", IS_WORKER, "第三方", IS_ADMIN, "管理员");
    public static final Object MANGER = "管理员";

    static {
        TYPE_MAP.put(IS_USER,"用户");
        TYPE_MAP.put(IS_ADMIN,"最高管理员");
    }

    public static final Map<String, Long> ROLE_MAP = new HashMap<>(); // Map.of(IS_USER, 2L, IS_WORKER, 3L, IS_ADMIN, 1L);
    static {
        ROLE_MAP.put(IS_USER, 2L);
        ROLE_MAP.put(IS_ADMIN, 1L);
    }
}