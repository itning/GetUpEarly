package top.itning.getupearly.net;

import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author itning
 */
public final class HttpHelper {

    private static Retrofit RETROFIT;

    /**
     * 初始化Retrofit
     */
    public static void initRetrofit() {
        String baseUrl = "https://gitee.com/itning/get-up-early/raw/master/";

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                // 设置超时时间
                .connectTimeout(15L, TimeUnit.SECONDS)
                // 设置读写时间
                .readTimeout(15L, TimeUnit.SECONDS)
                .build();
        RETROFIT = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    /**
     * 获取RETROFIT实例
     *
     * @param service 服务名
     * @param <T>     返回类型
     * @return 代理的实例
     */
    public static <T> T get(@NonNull final Class<T> service) {
        return RETROFIT.create(service);
    }
}
