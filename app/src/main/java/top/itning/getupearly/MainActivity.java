package top.itning.getupearly;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import top.itning.getupearly.config.SharedPreferencesConfig;
import top.itning.getupearly.factory.ViewFactory;
import top.itning.getupearly.strategy.StrategyContext;
import top.itning.getupearly.strategy.impl.UrlSchemeStrategy;


/**
 * @author itning
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Project GitHub Url
     */
    private static final String GITHUB_FOR_STAR = "https://github.com/itning/GetUpEarly";

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
        }
        return super.onOptionsItemSelected(item);
    }
}