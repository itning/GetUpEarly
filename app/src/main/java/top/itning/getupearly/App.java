package top.itning.getupearly;

import android.app.Application;

import top.itning.getupearly.net.HttpHelper;

/**
 * @author itning
 * @since 2020/12/8 12:54
 */
public class App extends Application {

    @Override
    public void onCreate() {
        HttpHelper.initRetrofit();
        super.onCreate();
    }

}