package net.soumoney.loan.dto;

/**
 * Created by jiangxiaojie on 2017/3/22.
 */
public class T06_loan_banner {
    private String bid;
    private String name;
    private String imgurl;
    private String url;
    private String pid;
    private String is_outer_link;
    private String createdate;
    private int seq;
    private String is_show;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getIs_outer_link() {
        return is_outer_link;
    }

    public void setIs_outer_link(String is_outer_link) {
        this.is_outer_link = is_outer_link;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }
}
