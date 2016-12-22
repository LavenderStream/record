package org.record.tiny.demo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("All")
public class Tab {
    @SerializedName("error_code")
    private int errorCode;
    @SerializedName("tip_msg")
    private String tipMsg;
    private List<DataBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int error_code) {
        this.errorCode = error_code;
    }

    public String getTip_msg() {
        return tipMsg;
    }

    public void setTip_msg(String tip_msg) {
        this.tipMsg = tip_msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        @SerializedName("tabs_name")
        private String tabsName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTabsName() {
            return tabsName;
        }

        public void setTabs_name(String tabs_name) {
            this.tabsName = tabs_name;
        }
    }
}
