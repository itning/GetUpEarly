package top.itning.getupearly.constant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * API包裹
 *
 * @author itning
 * @since 2020/8/11 9:01
 */
public class ApiBundle implements ViewItem<Api> {
    private final Api api;
    private final int order;

    public ApiBundle(@NonNull Api api) {
        this(api, Integer.MAX_VALUE);
    }

    public ApiBundle(@NonNull Api api, int order) {
        this.api = api;
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @NonNull
    @Override
    public Api getItem() {
        return api;
    }

    @Override
    public int hashCode() {
        return this.api.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ApiBundle) {
            ApiBundle apiBundle = (ApiBundle) obj;
            return api.equals(apiBundle.api);
        }
        return super.equals(obj);
    }

    @NonNull
    @Override
    public String toString() {
        return "ApiBundle{" +
                "api=" + api +
                ", order=" + order +
                '}';
    }
}
