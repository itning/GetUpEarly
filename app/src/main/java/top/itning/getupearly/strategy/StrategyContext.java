package top.itning.getupearly.strategy;

import android.content.ActivityNotFoundException;
import android.content.Context;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

import top.itning.getupearly.strategy.impl.QqLinkStrategy;
import top.itning.getupearly.strategy.impl.UrlSchemeStrategy;
import top.itning.getupearly.strategy.impl.WeiBoBrowserStrategy;

/**
 * 策略上下文
 *
 * @author itning
 * @since 2020/8/11 12:47
 */
public class StrategyContext {
    private final Context context;
    private final OpenStrategy openStrategy;

    public StrategyContext(@NonNull Context context, @NonNull OpenStrategy openStrategy) {
        this.context = context;
        this.openStrategy = openStrategy;
    }

    public StrategyContext(@NonNull Context context, @NonNull Strategy strategy) {
        this.context = context;
        this.openStrategy = getOpenStrategy(strategy);
    }

    @CheckResult
    @NonNull
    private OpenStrategy getOpenStrategy(@NonNull Strategy strategy) {
        switch (strategy) {
            case WEIBO: {
                return WeiBoBrowserStrategy.getInstance();
            }
            case QQ_LINK: {
                return QqLinkStrategy.getInstance();
            }
            default: {
                return UrlSchemeStrategy.getInstance();
            }
        }
    }

    public void open(@NonNull String uri) throws ActivityNotFoundException {
        openStrategy.open(uri, context);
    }
}
