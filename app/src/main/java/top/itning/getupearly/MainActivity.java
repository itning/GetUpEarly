package top.itning.getupearly;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


/**
 * @author itning
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    /**
     * 使用QQ内置浏览器URL模板
     */
    private static final String QQ_BROWSER_URL_TEMPLATE = "mqqapi://forward/url?url_prefix=%s&souce=oicqzone.com&version=1&src_type=web";
    /**
     * 蚂蚁森林
     */
    private static final String ALI_TREE_URL_SCHEME = "alipays://platformapi/startapp?saId=60000002";
    /**
     * 蚂蚁庄园
     */
    private static final String ALI_MANOR_URL_SCHEME = "alipays://platformapi/startapp?appId=66666674";
    /**
     * 阿里运动
     */
    private static final String ALI_SPORT_URL_SCHEME = "alipays://platformapi/startapp?saId=20000869";
    /**
     * QQ打卡
     */
    private static final String QQ_CHECK_IN_URL_PREFIX = "aHR0cHM6Ly90aS5xcS5jb20vc2lnbmluL3B1YmxpYy9pbmRleC5odG1sP193dj0xMDkwNTMyMjU3Jl93d3Y9MTM=";
    /**
     * QQ早起奖金
     */
    private static final String QQ_GET_UP_EARLY_URL_PREFIX = "aHR0cHM6Ly95dW5kb25nLnFxLmNvbS9wYWdlL3NpZ24vaW5kZXg/X3d2PTE4OTUwMTE1Jl93d3Y9MSZBRFRBRz1yZWRwa2cuYm9udXMucHVuY2hjYXJkJnN0ZXA9ODk1Mg==";
    /**
     * 微博早起奖金
     */
    private static final String WEIBO_GET_UP_EARLY_URL = "https://getup.sc.weibo.com/home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setElevation(0);
        }
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
    }

    public void onBtnClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.qq_check_in: {
                    startQqLink(QQ_CHECK_IN_URL_PREFIX);
                    break;
                }
                case R.id.qq_get_up_early: {
                    startQqLink(QQ_GET_UP_EARLY_URL_PREFIX);
                    break;
                }
                case R.id.weibo_get_up_early: {
                    openWeiBoBrowser(WEIBO_GET_UP_EARLY_URL);
                    break;
                }
                case R.id.ali_tree: {
                    startUrlScheme(ALI_TREE_URL_SCHEME);
                    break;
                }
                case R.id.ali_manor: {
                    startUrlScheme(ALI_MANOR_URL_SCHEME);
                    break;
                }
                case R.id.ali_sport: {
                    startUrlScheme(ALI_SPORT_URL_SCHEME);
                    break;
                }
                default:
                    Toast.makeText(this, "未知选项", Toast.LENGTH_LONG).show();
            }
        } catch (ActivityNotFoundException e) {
            Log.d(TAG, "app not found");
            Toast.makeText(this, "APP没有找到", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            String msg = "error detail: " + e.getMessage();
            Log.e(TAG, msg);
            new AlertDialog.Builder(this)
                    .setTitle("错误")
                    .setMessage(msg)
                    .show();
        }
    }

    /**
     * 启动urlScheme
     *
     * @param urlScheme urlScheme
     */
    private void startUrlScheme(@NonNull String urlScheme) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlScheme)));
    }

    /**
     * 使用QQ内置浏览器打开链接
     *
     * @param urlPrefix 网址（必须使用base64进行编码）
     */
    private void startQqLink(@NonNull String urlPrefix) {
        String uri = String.format(QQ_BROWSER_URL_TEMPLATE, urlPrefix);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    /**
     * 打开微博客户端内置浏览器
     *
     * @param url 要打开的网页地址
     */
    @SuppressWarnings("SameParameterValue")
    private void openWeiBoBrowser(@NonNull String url) {
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse("sinaweibo://browser?url=" + url));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            startUrlScheme(WEIBO_GET_UP_EARLY_URL);
        }
    }
}