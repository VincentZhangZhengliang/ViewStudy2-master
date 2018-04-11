package com.topzrt.viewstudy2.bean;

import java.util.List;

/**
 * Created by Vincent;
 * Created on 2017/12/18;
 * DSC:
 */

public class ss {


    /**
     * data : {"list":[{"hurl":"http://www.topzrt.com//wap/index.html?ctl=noviceregister&active=activity&from=app","img_src":"http://www.topzrt.com//static/wap/images/list_img1.png","title":"新手注册就送"},{"hurl":"http://www.topzrt.com//wap/experience.html?active=activity","img_src":"http://www.topzrt.com//static/wap/images/list_img2.png","title":"去赚钱"}]}
     * msg :
     * status : 1
     */

    private DataBean data;
    private String msg;
    private int    status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * hurl : http://www.topzrt.com//wap/index.html?ctl=noviceregister&active=activity&from=app
             * img_src : http://www.topzrt.com//static/wap/images/list_img1.png
             * title : 新手注册就送
             */

            private String hurl;
            private String img_src;
            private String title;

            public String getHurl() {
                return hurl;
            }

            public void setHurl(String hurl) {
                this.hurl = hurl;
            }

            public String getImg_src() {
                return img_src;
            }

            public void setImg_src(String img_src) {
                this.img_src = img_src;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
