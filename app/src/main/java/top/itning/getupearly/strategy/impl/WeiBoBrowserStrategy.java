package top.itning.getupearly.strategy.impl;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

import top.itning.getupearly.strategy.OpenStrategy;
import top.itning.getupearly.strategy.Strategy;

/**
 * @author itning
 * @since 2020/8/11 12:58
 */
public class WeiBoBrowserStrategy implements OpenStrategy {
    private WeiBoBrowserStrategy() {
    }

    private static final OpenStrategy WEIBO_BROWSER_STRATEGY = new WeiBoBrowserStrategy();

    public static OpenStrategy getInstance() {
        return WEIBO_BROWSER_STRATEGY;
    }

    @Override
    public boolean support(@NonNull Strategy strategy) {
        return strategy.equals(Strategy.WEIBO);
    }

    @Override
    public void open(@NonNull String uri, @NonNull Context context) {
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse("sinaweibo://browser?url=" + uri));
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            UrlSchemeStrategy.getInstance().open(uri, context);
        }
    }
}
