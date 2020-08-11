package top.itning.getupearly.config;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

import java.util.Map;

import top.itning.getupearly.constant.Api;
import top.itning.getupearly.constant.ViewItem;

/**
 * 用户配置
 *
 * @author itning
 * @date 2020/8/10 20:04
 */
public interface Config<T> {
    /**
     * 获取配置信息
     *
     * @return 映射
     */
    @NonNull
    @CheckResult
    Map<ViewItem<T>, Boolean> getConfig();

    /**
     * 保存配置信息
     *
     * @param api    API
     * @param isShow 是否显示
     * @param order  顺序
     */
    void saveConfig(@NonNull Api api, boolean isShow, int order);
}
