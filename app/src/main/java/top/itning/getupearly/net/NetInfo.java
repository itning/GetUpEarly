package top.itning.getupearly.net;

import io.reactivex.Observable;
import retrofit2.http.GET;
import top.itning.getupearly.entity.UpdateInfo;

/**
 * @author itning
 * @since 2020/12/8 10:09
 */
public interface NetInfo {

    /**
     * 获取更新信息
     *
     * @return UpdateInfo
     */
    @GET("update.json")
    Observable<UpdateInfo> get();
}
