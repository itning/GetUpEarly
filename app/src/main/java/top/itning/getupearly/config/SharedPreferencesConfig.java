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
            map.put(new ApiBundle(api, sharedPref.getInt(api.name(), Integer.MAX_VALUE)), sharedPref.getBoolean(api.name(), true));
        }
        return map;
    }

    @Override
    public void saveConfig(@NonNull Api api, boolean isShow, int order) {
        sharedPref
                .edit()
                .putBoolean(api.name(), isShow)
                .putInt(api.name(), order)
                .apply();
    }
}
