package top.itning.getupearly.constant;

import androidx.annotation.NonNull;

/**
 * 每一项
 *
 * @author itning
 * @date 2020/8/11 8:52
 */
public interface ViewItem<T> {
    /**
     * 获取顺序
     *
     * @return 顺序
     */
    int getOrder();

    /**
     * 获取某一项
     *
     * @return 某一项
     */
    @NonNull
    T getItem();
}
