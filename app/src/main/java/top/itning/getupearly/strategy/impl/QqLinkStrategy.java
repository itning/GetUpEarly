package top.itning.getupearly.strategy.impl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

import top.itning.getupearly.strategy.OpenStrategy;
import top.itning.getupearly.strategy.Strategy;

/**
 * @author itning
 * @date 2020/8/11 13:17
 */
public class QqLinkStrategy implements OpenStrategy {
    private static final OpenStrategy QQ_LINK_STRATEGY = new QqLinkStrategy();
    private static final String QQ_BROWSER_URL_TEMPLATE = "mqqapi://forward/url?url_prefix=%s&souce=oicqzone.com&version=1&src_type=web";

    public static OpenStrategy getInstance() {
        return QQ_LINK_STRATEGY;
    }

    private QqLinkStrategy() {
    }


    @Override
    public boolean support(@NonNull Strategy strategy) {
        return strategy.equals(Strategy.QQ_LINK);
    }

    @Override
    public void open(@NonNull String uri, @NonNull Context context) {
        String formatUri = String.format(QQ_BROWSER_URL_TEMPLATE, uri);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(formatUri));
        context.startActivity(intent);
    }
}
