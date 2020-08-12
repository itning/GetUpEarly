package top.itning.getupearly.ui.callback;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import top.itning.getupearly.ui.adapter.ViewSettingAdapter;
import top.itning.getupearly.ui.entity.ItemData;

/**
 * @author itning
 * @date 2020/8/11 15:56
 */
public class ViewItemTouchHelperCallBack extends ItemTouchHelper.Callback {
    private final List<ItemData> list;
    private final ViewSettingAdapter adapter;

    public ViewItemTouchHelperCallBack(@NonNull List<ItemData> list, @NonNull ViewSettingAdapter adapter) {
        this.list = list;
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        // 也就是说返回值是组合式的
        // makeMovementFlags (int dragFlags, int swipeFlags)，看下面的解释说明
        int swipFlag = 0;
        // 如果也监控左右方向的话，swipFlag=ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        int dragflag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        // 等价于：0001&0010;多点触控标记触屏手指的顺序和个数也是这样标记哦
        return makeMovementFlags(dragflag, swipFlag);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        // 直接按照文档来操作啊，这文档写得太给力了,简直完美！
        adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        // 注意这里有个坑的，itemView 都移动了，对应的数据也要移动
        Collections.swap(list, viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE && null != viewHolder) {
            viewHolder.itemView.setBackgroundColor(Color.BLACK);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(Color.parseColor("#ff212121"));
    }
}
