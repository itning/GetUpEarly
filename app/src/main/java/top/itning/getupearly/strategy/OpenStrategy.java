package top.itning.getupearly.strategy;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * 打开策略
 *
 * @author itning
 * @date 2020/8/11 12:46
 */
public interface OpenStrategy {
    /**
     * 支持这种打开么
     *
     * @param strategy Strategy
     * @return 支持与否
     */
    boolean support(@NonNull Strategy strategy);

    /**
     * 打开
     *
     * @param uri     URI
     * @param context 上下文
     */
    void open(@NonNull String uri,@NonNull Context context);
}
