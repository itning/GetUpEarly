package top.itning.getupearly.config;

import android.content.SharedPreferences;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import top.itning.getupearly.constant.Api;
import top.itning.getupearly.constant.ApiBundle;
import top.itning.getupearly.constant.ViewItem;

/**
 * SharedPreferences实现配置
 *
 * @author itning
 * @date 2020/8/10 20:07
 */
public class SharedPreferencesConfig implements Config<Api> {

    private static final String ORDER_PREFIX = "ORDER_";

    private static final String IS_SHOW_PREFIX = "SHOW_";

    @NonNull
    private final SharedPreferences sharedPref;

    public SharedPreferencesConfig(@NonNull SharedPreferences sharedPref) {
        this.sharedPref = sharedPref;
    }

    @Override
    @NonNull
    @CheckResult
    public Map<ViewItem<Api>, Boolean> getConfig() {
        Map<ViewItem<Api>, Boolean> map = new HashMap<>();
        for (Api api : Api.values()) {
            map.put(new ApiBundle(api, sharedPref.getInt(getOrderKey(api.name()), Integer.MAX_VALUE)), sharedPref.getBoolean(getIsShowKey(api.name()), true));
        }
        return map;
    }

    @Override
    public void saveConfig(@NonNull Api api, boolean isShow, int order) {
        sharedPref
                .edit()
                .putBoolean(getIsShowKey(api.name()), isShow)
                .putInt(getOrderKey(api.name()), order)
                .apply();
    }

    public static String getOrderKey(String value) {
        return ORDER_PREFIX + value;
    }

    public static String getIsShowKey(String value) {
        return IS_SHOW_PREFIX + value;
    }
}
