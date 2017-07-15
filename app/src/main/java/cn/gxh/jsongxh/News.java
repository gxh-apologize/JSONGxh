package cn.gxh.jsongxh;

import java.util.List;

/**
 * Created by GXH on 2017/7/14.
 */
public class News {


    /**
     * data : {"countcommenturl":"http://zhbj.qianlong.com/client/content/countComment/","more":"/10002/list_2.json","news":[{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35311,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2015-09-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35312,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2015-09-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"}],"title":"专题"}
     * retcode : 200
     */

    private DataBean data;
    private int retcode;

    public News() {
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public static class DataBean {
        /**
         * countcommenturl : http://zhbj.qianlong.com/client/content/countComment/
         * more : /10002/list_2.json
         * news : [{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35311,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2015-09-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35312,"listimage":"/10002/1452327318UU91.jpg","pubdate":"2015-09-08 14:58","title":"专题","type":"news","url":"/10002/724D6A55496A11726628.html"}]
         * title : 专题
         */

        private String countcommenturl;
        private String more;
        private String title;
        private List<NewsBean> news;

        public String getCountcommenturl() {
            return countcommenturl;
        }

        public void setCountcommenturl(String countcommenturl) {
            this.countcommenturl = countcommenturl;
        }

        public String getMore() {
            return more;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }

        public static class NewsBean {
            /**
             * comment : true
             * commentlist : /10002/comment_1.json
             * commenturl : http://zhbj.qianlong.com/client/user/newComment/35319
             * id : 35311
             * listimage : /10002/1452327318UU91.jpg
             * pubdate : 2015-09-08 14:58
             * title : 专题
             * type : news
             * url : /10002/724D6A55496A11726628.html
             */

            private boolean comment;
            private String commentlist;
            private String commenturl;
            private int id;
            private String listimage;
            private String pubdate;
            private String title;
            private String type;
            private String url;

            public boolean isComment() {
                return comment;
            }

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public String getCommentlist() {
                return commentlist;
            }

            public void setCommentlist(String commentlist) {
                this.commentlist = commentlist;
            }

            public String getCommenturl() {
                return commenturl;
            }

            public void setCommenturl(String commenturl) {
                this.commenturl = commenturl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getListimage() {
                return listimage;
            }

            public void setListimage(String listimage) {
                this.listimage = listimage;
            }

            public String getPubdate() {
                return pubdate;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
