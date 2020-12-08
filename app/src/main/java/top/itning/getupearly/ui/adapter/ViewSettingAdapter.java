package top.itning.getupearly.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import top.itning.getupearly.R;
import top.itning.getupearly.ui.entity.ItemData;

/**
 * @author itning
 * @since 2020/8/11 15:04
 */
public class ViewSettingAdapter extends RecyclerView.Adapter<ViewSettingAdapter.SettingViewHolder> {
    private final AppCompatActivity activity;
    private final List<ItemData> list;

    public ViewSettingAdapter(@NonNull AppCompatActivity activity, @NonNull List<ItemData> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_view_setting, parent, false);
        return new SettingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position) {
        ItemData itemData = list.get(position);
        holder.textView.setText(itemData.getApi().getStrRes());
        holder.textView.setTag(holder.checkBox);
        holder.textView.setOnClickListener(v -> {
            AppCompatCheckBox checkBox = (AppCompatCheckBox) v.getTag();
            checkBox.setChecked(!checkBox.isChecked());
        });
        holder.checkBox.setChecked(itemData.isShow());
        holder.checkBox.setTag(itemData);
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> ((ItemData) buttonView.getTag()).setShow(isChecked));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class SettingViewHolder extends RecyclerView.ViewHolder {
        AppCompatCheckBox checkBox;
        AppCompatTextView textView;

        public SettingViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.cb_item_view);
            textView = itemView.findViewById(R.id.tv_item_view);
        }
    }
}
