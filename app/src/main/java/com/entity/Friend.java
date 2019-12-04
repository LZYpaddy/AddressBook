package com.entity;

public class Friend {
    private String name;
    private int imageId;
    private int buttonId;

    public Friend() {

    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    private String tel;
    public Friend(String name, String tel){
        this.name = name;
        this.tel = tel;
    }
    public Friend(String name, int imageId, int buttonId, String tel) {
        this.name = name;
        this.imageId = imageId;
        this.buttonId = buttonId;
        this.tel = tel;
    }

    public int getButtonId() {
        return buttonId;
    }

    public void setButtonId(int buttonId) {
        this.buttonId = buttonId;
    }

    public Friend(String name, int imageId, int buttonId) {
        this.name = name;
        this.imageId = imageId;
        this.buttonId = buttonId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setName(String name) {
        this.name = name;
    }
}