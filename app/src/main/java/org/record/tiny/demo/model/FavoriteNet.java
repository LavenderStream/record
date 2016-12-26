package org.record.tiny.demo.model;

import java.util.List;

/**
 * Created by tiny on 12/26/2016.
 */

public class FavoriteNet {

    private int error_code;
    private DataBean data;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String count;
        private List<RowsBean> rows;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * story_id : 149
             * title : 测试 2015-11-20 18:08:14
             * image : http://ww2.sinaimg.cn/large/3dc7c371jw1exo6uuu523j20hs0dc3zu.jpg
             * summary : 内容
             * create_time : 1448014095
             * user : {"uid":"190","nickname":"39ee3cf490","small_avatar":"","large_avatar":""}
             * fav_count : 1
             * like_count : 1
             * comment_count : 1
             */

            private int story_id;
            private String title;
            private String image;
            private String summary;
            private String create_time;
            private UserBean user;
            private String fav_count;
            private String like_count;
            private String comment_count;

            public int getStory_id() {
                return story_id;
            }

            public void setStory_id(int story_id) {
                this.story_id = story_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public String getFav_count() {
                return fav_count;
            }

            public void setFav_count(String fav_count) {
                this.fav_count = fav_count;
            }

            public String getLike_count() {
                return like_count;
            }

            public void setLike_count(String like_count) {
                this.like_count = like_count;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public static class UserBean {
                /**
                 * uid : 190
                 * nickname : 39ee3cf490
                 * small_avatar :
                 * large_avatar :
                 */

                private String uid;
                private String nickname;
                private String small_avatar;
                private String large_avatar;

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

                public String getSmall_avatar() {
                    return small_avatar;
                }

                public void setSmall_avatar(String small_avatar) {
                    this.small_avatar = small_avatar;
                }

                public String getLarge_avatar() {
                    return large_avatar;
                }

                public void setLarge_avatar(String large_avatar) {
                    this.large_avatar = large_avatar;
                }
            }
        }
    }
}
