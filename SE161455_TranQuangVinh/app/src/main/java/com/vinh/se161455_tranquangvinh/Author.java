package com.vinh.se161455_tranquangvinh;

import androidx.annotation.NonNull;

public class Author {
    private int authorId;
    private String name;
    private String address;
    private String phoneNum;

    public Author(int authorId, String name, String address, String phoneNum) {
        this.authorId = authorId;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @NonNull
    @Override
    public String toString() {
        return authorId + " - " + name + " - " + address + " - " + phoneNum;
    }
}