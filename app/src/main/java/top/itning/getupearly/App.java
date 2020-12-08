package top.itning.getupearly;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

import top.itning.getupearly.net.HttpHelper;

/**
 * @author itning
 * @since 2020/12/8 12:54
 */
public class App extends Application {

    @Override
    public void onCreate() {
        CrashReport.initCrashReport(getApplicationContext(), "e44358e199", false);
        HttpHelper.initRetrofit();
        super.onCreate();
    }

}