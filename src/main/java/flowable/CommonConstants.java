package flowable;

import java.math.BigDecimal;

/**
 * Created by liwei on 2017/8/3
 */
public class CommonConstants {
    public static final String LIST_CANNOT_BE_NULL = "Error: entityList must not be empty";
    public static final String UPLOAD_IMAGE = "请上传图片";


    public static final BigDecimal PAY_SCALE = new BigDecimal(100);
    public static final String STATUS = "status";
    public static final String SEARCH_OBJ_NULL = "查询对象不能为空";
    public static final String TASK_PLAN_START_DATE = "TASK_PLAN_START_DATE";
    public static final String TASK_PLAN_END_DATE = "TASK_PLAN_END_DATE";

    public static final String FACTORY_WORKFLOW = "FACTORY_WORKFLOW";
    public static final String FACTORY_WORKFLOW_NAME = "FAC_{}_{}";


    public static final String FACTORY_WORKFLOW_TEMPLATE = "FACTORY_WORKFLOW_TEMPLATE";
    public static final String PRO_DEF_CATEGORY = "RUN_CONFIG";

    public static final String PROCESS_VARIABLE_PRODUCT_ID = "PRODUCT_ID";
    public static final String PROCESS_VARIABLE_PROJECT_ID = "PROJECT_ID";
    public static final String REWORK_NAME_SET = "REWORK_NAME_SET";
    public static final Integer ENABLED = 0;
    public static final Integer DISABLED = 1;
    public static final Integer FACTORY_BACKGROUND = 0;
    public static final Integer FACTORY_FRONT = 1;
    //一天毫秒数
    public static final long ONE_DAY_LONG = 86400000;
    public static final int ONE_DAT_SECOND = 86400;
    //一小时秒数
    public static final long ONE_HOUR_LONG = 3600000;
    /**
     *
     */
    public final static int DAY_100 = 60 * 60 * 24 * 100;
    //合同状态
    public final static int DAY_30 = 60 * 60 * 24 * 30;
    public static final String LINE_FEED = System.getProperty("line.separator");
    /**
     * 手机验证码有效时间一分钟 (单位s)
     */
    public static final String PAGE_NUM = "pageNum";
    public static final String PAGE_SIZE = "pageSize";
    /**
     * 上传的报价excel信息保存三天
     */
    public static int QUOTE_EXCEL_STATUS_TIME = 60 * 60 * 24 * 3;
    public static int THREEDAYSECONDS = 60 * 60 * 24 * 3;

    private CommonConstants() {
    }


}



























