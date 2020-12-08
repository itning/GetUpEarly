package top.itning.getupearly.factory;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import top.itning.getupearly.R;
import top.itning.getupearly.config.Config;
import top.itning.getupearly.constant.Api;
import top.itning.getupearly.constant.ViewItem;
import top.itning.getupearly.strategy.StrategyContext;

/**
 * 视图工厂
 *
 * @author itning
 * @since 2020/8/11 8:17
 */
public class ViewFactory extends AbstractViewFactory<Api> {
    private static final String TAG = "ViewFactory";

    private final Config<Api> config;
    private final Context context;

    public ViewFactory(Config<Api> config, Context context) {
        this.config = config;
        this.context = context;
    }

    @NonNull
    @Override
    public List<ViewItem<Api>> generateList() {
        List<ViewItem<Api>> list = new ArrayList<>();
        for (Map.Entry<ViewItem<Api>, Boolean> entry : config.getConfig().entrySet()) {
            if (entry.getValue().equals(true)) {
                list.add(entry.getKey());
            }
        }
        return list;
    }

    @Override
    public void sort(@NonNull List<ViewItem<Api>> list) {
        Collections.sort(list, (a, b) -> Integer.compare(a.getOrder(), b.getOrder()));
    }

    @NonNull
    @Override
    public View getView(@NonNull ViewItem<Api> viewItem) {
        ContextThemeWrapper newContext = new ContextThemeWrapper(context, R.style.AppTheme);
        AppCompatButton appCompatButton = new AppCompatButton(newContext);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f);
        appCompatButton.setLayoutParams(layoutParams);

        appCompatButton.setGravity(Gravity.CENTER);

        ColorStateList colorStateList = ResourcesCompat.getColorStateList(context.getResources(), R.color.colorPrimary, null);
        Drawable drawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ripple_bg, null);
        appCompatButton.setBackgroundTintList(colorStateList);
        appCompatButton.setBackground(drawable);

        appCompatButton.setTextColor(Color.WHITE);
        appCompatButton.setText(viewItem.getItem().getStrRes());

        return appCompatButton;
    }

    @Override
    public void onItemClick(@NonNull Api api) {
        StrategyContext strategyContext = new StrategyContext(context, api.getStrategy());
        try {
            strategyContext.open(api.getUrl());
        } catch (ActivityNotFoundException e) {
            Log.w(TAG, "app not found " + e.getMessage());
            Toast.makeText(context, "APP没有找到", Toast.LENGTH_LONG).show();
        }
    }
}
