package top.itning.getupearly.ui.entity;

import top.itning.getupearly.constant.Api;

/**
 * @author itning
 * @since 2020/8/11 15:33
 */
public class ItemData {
    private Api api;
    private boolean show;
    private int order;

    public ItemData() {
    }

    public ItemData(Api api, boolean show, int order) {
        this.api = api;
        this.show = show;
        this.order = order;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
