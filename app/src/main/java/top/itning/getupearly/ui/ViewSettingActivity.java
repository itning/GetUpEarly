package top.itning.getupearly.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import top.itning.getupearly.R;
import top.itning.getupearly.config.Config;
import top.itning.getupearly.config.SharedPreferencesConfig;
import top.itning.getupearly.constant.Api;
import top.itning.getupearly.ui.adapter.ViewSettingAdapter;
import top.itning.getupearly.ui.callback.ViewItemTouchHelperCallBack;
import top.itning.getupearly.ui.entity.ItemData;

import static top.itning.getupearly.config.SharedPreferencesConfig.getIsShowKey;
import static top.itning.getupearly.config.SharedPreferencesConfig.getOrderKey;

/**
 * 视图设置
 *
 * @author itning
 */
public class ViewSettingActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private List<ItemData> itemDataList;
    private ViewItemTouchHelperCallBack callBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_setting);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(R.string.view_setting_str);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setElevation(0);
        }
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("app_config", MODE_PRIVATE);

        RecyclerView view = findViewById(R.id.rv_root);
        view.setLayoutManager(new LinearLayoutManager(this));

        itemDataList = getItemDataList();

        ViewSettingAdapter adapter = new ViewSettingAdapter(this, itemDataList);

        view.setAdapter(adapter);

        callBack = new ViewItemTouchHelperCallBack(itemDataList, adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBack);
        itemTouchHelper.attachToRecyclerView(view);

        Toast.makeText(this, R.string.sort_tip_toast_str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                this.finish();
                return true;
            }
            case R.id.save_config: {
                saveConfig();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    private void saveConfig() {
        Config<Api> config = new SharedPreferencesConfig(sharedPreferences);
        int i = 0;
        for (ItemData itemData : itemDataList) {
            config.saveConfig(itemData.getApi(), itemData.isShow(), i++);
        }
        Toast.makeText(this, R.string.save_success_str, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_setting_menu, menu);
        return true;
    }

    private List<ItemData> getItemDataList() {
        List<ItemData> list = new ArrayList<>();
        for (Api api : Api.values()) {
            ItemData itemData = new ItemData();
            int order = sharedPreferences.getInt(getOrderKey(api.name()), Integer.MAX_VALUE);
            boolean isShow = sharedPreferences.getBoolean(getIsShowKey(api.name()), true);
            itemData.setApi(api);
            itemData.setOrder(order);
            itemData.setShow(isShow);
            list.add(itemData);
        }
        return list;
    }
}