package top.itning.getupearly.strategy.impl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

import top.itning.getupearly.strategy.OpenStrategy;
import top.itning.getupearly.strategy.Strategy;

/**
 * @author itning
 * @since 2020/8/11 13:13
 */
public class UrlSchemeStrategy implements OpenStrategy {
    private UrlSchemeStrategy() {
    }

    private static final OpenStrategy URL_SCHEME_STRATEGY = new UrlSchemeStrategy();

    public static OpenStrategy getInstance() {
        return URL_SCHEME_STRATEGY;
    }

    @Override
    public boolean support(@NonNull Strategy strategy) {
        return strategy.equals(Strategy.URL_SCHEME);
    }

    @Override
    public void open(@NonNull String uri, @NonNull Context context) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
    }
}
