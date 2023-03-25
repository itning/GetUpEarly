package top.itning.getupearly;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import top.itning.getupearly.config.SharedPreferencesConfig;
import top.itning.getupearly.factory.ViewFactory;
import top.itning.getupearly.net.HttpHelper;
import top.itning.getupearly.net.NetInfo;
import top.itning.getupearly.strategy.Strategy;
import top.itning.getupearly.strategy.StrategyContext;
import top.itning.getupearly.strategy.impl.UrlSchemeStrategy;
import top.itning.getupearly.ui.ViewSettingActivity;


/**
 * @author itning
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    /**
     * Project GitHub Url
     */
    private static final String GITHUB_FOR_STAR = "https://github.com/itning/GetUpEarly";

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setElevation(0);
        }
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        LinearLayout linearLayout = findViewById(R.id.ll_root);
        SharedPreferencesConfig config = new SharedPreferencesConfig(getSharedPreferences("app_config", MODE_PRIVATE));
        ViewFactory viewFactory = new ViewFactory(config, this);
        viewFactory.start(linearLayout);
        getInfoFromNet();
    }

    private void getInfoFromNet() {
        disposable = HttpHelper.get(NetInfo.class)
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(info -> {
                    Log.i(TAG, "Get Net Info：" + info.toString());
                    if (info.isNeedCheckVersionCode() && info.getVersionCode() <= BuildConfig.VERSION_CODE) {
                        return;
                    }
                    new AlertDialog.Builder(this, R.style.MyAlertDialogStyle)
                            .setTitle(info.getTitle())
                            .setMessage(info.getDesc())
                            .setNegativeButton(null == info.getNegativeButtonText() ? getResources().getString(R.string.check_update_negative_button_str) : info.getNegativeButtonText(), null)
                            .setPositiveButton(null == info.getPositiveButtonText() ? getResources().getString(R.string.check_update_positive_button_str) : info.getPositiveButtonText(), (dialog, which) -> {
                                if (null != info.getLink()) {
                                    new StrategyContext(this, Strategy.URL_SCHEME).open(info.getLink());
                                }
                            })
                            .show();

                }, e -> {
                    Log.e(TAG, "Get Net Info Exception", e);
                    Toast.makeText(this, R.string.check_update_failed_str, Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, Menu.NONE, Menu.NONE, getString(R.string.app_version_str, BuildConfig.VERSION_NAME));
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.github_for_star) {
            new StrategyContext(this, UrlSchemeStrategy.getInstance()).open(GITHUB_FOR_STAR);
            return true;
        } else if (item.getItemId() == R.id.view_setting) {
            startActivity(new Intent(this, ViewSettingActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }
}