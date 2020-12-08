package top.itning.getupearly.factory;


import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import top.itning.getupearly.constant.ViewItem;

/**
 * 抽象视图工厂
 *
 * @author itning
 * @since 2020/8/10 19:54
 */
public abstract class AbstractViewFactory<T> implements View.OnClickListener {

    /**
     * 模板方法
     *
     * @param linearLayout LinearLayout
     */
    public final void start(@NonNull LinearLayout linearLayout) {
        List<ViewItem<T>> viewItems = generateList();
        sort(viewItems);
        List<View> generateView = generateView(viewItems);
        inflaterLayout(generateView, linearLayout);
    }

    /**
     * 生成每一项
     *
     * @return 集合
     */
    @NonNull
    public abstract List<ViewItem<T>> generateList();

    /**
     * 对集合进行排序
     *
     * @param list 集合
     */
    public abstract void sort(@NonNull List<ViewItem<T>> list);

    @NonNull
    public List<View> generateView(@NonNull List<ViewItem<T>> list) {
        List<View> viewList = new ArrayList<>();
        for (ViewItem<T> viewItem : list) {
            View view = getView(viewItem);
            view.setOnClickListener(this);
            view.setTag(viewItem.getItem());
            viewList.add(view);
        }
        return viewList;
    }

    /**
     * 获取一个View
     *
     * @param viewItem 某一项View
     * @return View
     */
    @NonNull
    public abstract View getView(@NonNull ViewItem<T> viewItem);

    public void inflaterLayout(List<View> list, LinearLayout linearLayout) {
        for (View view : list) {
            linearLayout.addView(view);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void onClick(View v) {
        onItemClick((T) v.getTag());
    }

    /**
     * 当某一项被点击时
     *
     * @param t 被点击的内容
     */
    public abstract void onItemClick(@NonNull T t);
}
