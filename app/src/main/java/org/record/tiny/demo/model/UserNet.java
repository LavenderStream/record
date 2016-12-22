package org.record.tiny.demo.model;

import java.util.List;

public class UserNet {

    private int error_code;
    private String tip_msg;
    private DataBean data;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getTip_msg() {
        return tip_msg;
    }

    public void setTip_msg(String tip_msg) {
        this.tip_msg = tip_msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String access_token;
        private String uid;
        private String nickname;
        private String avatar;
        private String load_url;
        private List<BindPartnersBean> bind_partners;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getLoad_url() {
            return load_url;
        }

        public void setLoad_url(String load_url) {
            this.load_url = load_url;
        }

        public List<BindPartnersBean> getBind_partners() {
            return bind_partners;
        }

        public void setBind_partners(List<BindPartnersBean> bind_partners) {
            this.bind_partners = bind_partners;
        }

        public static class BindPartnersBean {

            private String partner;
            private String open_id;
            private String nickname;
            private String avatar;

            public String getPartner() {
                return partner;
            }

            public void setPartner(String partner) {
                this.partner = partner;
            }

            public String getOpen_id() {
                return open_id;
            }

            public void setOpen_id(String open_id) {
                this.open_id = open_id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
    }
}
