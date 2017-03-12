package com.svnit.harsimar.myclassroom;

public class bc_connect_posts {
    private String image,desc,title;

    public bc_connect_posts() {

    }

    public bc_connect_posts(String image, String desc, String title) {
        this.image = image;
        this.desc = desc;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
