package top.itning.getupearly.entity;

import java.io.Serializable;

/**
 * @author itning
 * @since 2020/12/8 10:10
 */
public class UpdateInfo implements Serializable {
    private int versionCode;
    private String versionName;
    private String link;
    private String desc;
    private String title;
    private boolean needCheckVersionCode;
    private String negativeButtonText;
    private String positiveButtonText;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isNeedCheckVersionCode() {
        return needCheckVersionCode;
    }

    public void setNeedCheckVersionCode(boolean needCheckVersionCode) {
        this.needCheckVersionCode = needCheckVersionCode;
    }

    public String getNegativeButtonText() {
        return negativeButtonText;
    }

    public void setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
    }

    public String getPositiveButtonText() {
        return positiveButtonText;
    }

    public void setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
    }

    @Override
    public String toString() {
        return "UpdateInfo{" +
                "versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", link='" + link + '\'' +
                ", desc='" + desc + '\'' +
                ", title='" + title + '\'' +
                ", needCheckVersionCode=" + needCheckVersionCode +
                ", negativeButtonText='" + negativeButtonText + '\'' +
                ", positiveButtonText='" + positiveButtonText + '\'' +
                '}';
    }
}
