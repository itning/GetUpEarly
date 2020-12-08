package top.itning.getupearly.constant;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import top.itning.getupearly.R;
import top.itning.getupearly.strategy.Strategy;

/**
 * API枚举
 *
 * @author itning
 * @since 2020/8/10 19:33
 */
public enum Api {
    /**
     * 蚂蚁森林
     */
    ALI_TREE_URL_SCHEME(R.string.ali_tree_str, Strategy.URL_SCHEME, "alipays://platformapi/startapp?saId=60000002"),
    /**
     * 蚂蚁庄园
     */
    ALI_MANOR_URL_SCHEME(R.string.ali_manor_str, Strategy.URL_SCHEME, "alipays://platformapi/startapp?appId=66666674"),
    /**
     * 阿里运动
     */
    ALI_SPORT_URL_SCHEME(R.string.ali_sport_str, Strategy.URL_SCHEME, "alipays://platformapi/startapp?saId=20000869"),
    /**
     * QQ打卡
     */
    QQ_CHECK_IN_URL_PREFIX(R.string.qq_check_in_str, Strategy.QQ_LINK, "aHR0cHM6Ly90aS5xcS5jb20vc2lnbmluL3B1YmxpYy9pbmRleC5odG1sP193dj0xMDkwNTMyMjU3Jl93d3Y9MTM="),
    /**
     * QQ早起奖金
     */
    QQ_GET_UP_EARLY_URL_PREFIX(R.string.qq_get_up_early_str, Strategy.QQ_LINK, "aHR0cHM6Ly95dW5kb25nLnFxLmNvbS9wYWdlL3NpZ24vaW5kZXg/X3d2PTE4OTUwMTE1Jl93d3Y9MSZBRFRBRz1yZWRwa2cuYm9udXMucHVuY2hjYXJkJnN0ZXA9ODk1Mg=="),
    /**
     * 微博早起奖金
     */
    WEIBO_GET_UP_EARLY_URL(R.string.weibo_get_up_early_str, Strategy.WEIBO, "https://getup.sc.weibo.com/home"),
    /**
     * 支付宝早起打卡挑战赛
     */
    ALI_SPORTS_GETUP_SERVER_URL_SCHEME(R.string.ali_sport_server_str, Strategy.URL_SCHEME, "alipays://platformapi/startapp?appId=20000067&url=https%3a%2f%2factivity-alisports.taobao.com%2fgetup"),
    /**
     * 支付宝天天红包赛
     */
    ALI_DD_SPORTS_SERVER_URL_SCHEME(R.string.ali_dd_server_str, Strategy.URL_SCHEME, "alipays://platformapi/startapp?appId=20000067&url=https%3a%2f%2fhuodong.taobao.com%2fwow%2ftyact%2fact%2fddsports-home-alipay%3fgame_type%3d1"),
    /**
     * 淘宝早起打卡挑战赛
     */
    TAOBAO_GETUP_SERVER_URL_SCHEME(R.string.taobao_get_up_early_str, Strategy.URL_SCHEME, "taobao://activity-alisports.taobao.com/p/taobao/activity/getup_early/m_home.html?game_type=9"),
    /**
     * 京东金融早起打卡
     */
    JD_GET_UP_EARLY_URL_SCHEME(R.string.jd_get_up_early_str, Strategy.URL_SCHEME, "jdmobile://share?jumpType=7&jumpUrl=215"),
    /**
     * 支付宝健康早起打卡
     */
    ALI_HEALTH_GET_UP_EARLY_URL_SCHEME(R.string.ali_health_get_up_early_str, Strategy.URL_SCHEME, "alipays://platformapi/startapp?appId=2018091361395351&page=pages/signin/index"),
    /**
     * 支付宝运动打卡领健康金
     */
    ALI_HEALTH_SPORTS_URL_SCHEME(R.string.ali_health_sports_early_str, Strategy.URL_SCHEME, "alipays://platformapi/startapp?appId=2018091361395351&page=pages/sport/index"),
    ;
    /**
     * 打开策略
     */
    private final Strategy strategy;
    /**
     * URI
     */
    private final String url;
    /**
     * 标题
     */
    private final int strRes;

    Api(@StringRes int strRes, Strategy strategy, String url) {
        this.strRes = strRes;
        this.strategy = strategy;
        this.url = url;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    @StringRes
    public int getStrRes() {
        return strRes;
    }

    @NonNull
    public Strategy getStrategy() {
        return strategy;
    }
}
