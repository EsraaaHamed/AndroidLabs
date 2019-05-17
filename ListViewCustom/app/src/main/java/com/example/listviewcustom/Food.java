package com.example.listviewcustom;

public class Food {
    String name;
    String desc;
    int imgIcon;

    public Food() {
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", imgIcon=" + imgIcon +
                '}';
    }

    public Food(String name, String desc, int imgIcon) {
        this.name = name;
        this.desc = desc;
        this.imgIcon = imgIcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(int imgIcon) {
        this.imgIcon = imgIcon;
    }
}
