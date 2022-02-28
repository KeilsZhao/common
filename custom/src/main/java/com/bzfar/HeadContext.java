package com.bzfar;



/**
 * 法院代码线程资源共享
 */
public class HeadContext {

    /** 法院代码 */
    private static final InheritableThreadLocal<String> FYDM_CONTEXT = new InheritableThreadLocal<>();

    /** 法院名称 */
    private static final InheritableThreadLocal<String> COURT_CONTEXT = new InheritableThreadLocal<>();

    /** 法院编号 */
    private static final InheritableThreadLocal<String> NUM_CONTEXT = new InheritableThreadLocal<>();

    /** 用户ID */
    private static final InheritableThreadLocal<Integer> USER_ID_CONTEXT = new InheritableThreadLocal<>();

    /** 设备编号 */
    private static final InheritableThreadLocal<String> DEVICE_NUM_CONTEXT = new InheritableThreadLocal<>();

    /** token */
    private static final InheritableThreadLocal<String> TOKEN_CONTEXT = new InheritableThreadLocal<>();

    /** mysql */
    private static final InheritableThreadLocal<String> MYSQL_SUFFIX = new InheritableThreadLocal<>();

    public static void setMysqlSuffix(String mysqlSuffix){ MYSQL_SUFFIX.set(mysqlSuffix);};

    public static String getMysqlSuffix(){return MYSQL_SUFFIX.get();};

    public static void setFydm(String fydm){ FYDM_CONTEXT.set(fydm);};

    public static String getFydm(){return FYDM_CONTEXT.get();};

    public static void setNum(String num){ NUM_CONTEXT.set(num);};

    public static String getNum(){return NUM_CONTEXT.get();};

    public static void setCourt(String name){ COURT_CONTEXT.set(name);};

    public static String getCourt(){return COURT_CONTEXT.get();};

    public static void setToken(String token){TOKEN_CONTEXT.set(token);}

    public static String getToken(){return TOKEN_CONTEXT.get();};

    public static void setUserId(Integer userId){USER_ID_CONTEXT.set(userId);}

    public static Integer getUserId(){return USER_ID_CONTEXT.get();};

    public static void setDeviceNum(String deviceNum){DEVICE_NUM_CONTEXT.set(deviceNum);}

    public static String getDeviceNum(){return DEVICE_NUM_CONTEXT.get();};

    public void clear(){
        FYDM_CONTEXT.remove();
        COURT_CONTEXT.remove();
        TOKEN_CONTEXT.remove();
        DEVICE_NUM_CONTEXT.remove();
    };
}
