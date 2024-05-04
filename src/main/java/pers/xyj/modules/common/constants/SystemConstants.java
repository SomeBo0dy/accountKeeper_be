package pers.xyj.modules.common.constants;

import java.util.HashMap;
import java.util.Map;

public class SystemConstants
{
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
    public static final Integer CODE_TIMEOUT = 15 * 60;

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