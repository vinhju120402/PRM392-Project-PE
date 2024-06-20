package com.vinh.se161455_tranquangvinh;

import androidx.annotation.NonNull;

public class Book {
    private int bookID;
    private String bookName;
    private String publicDate;
    private String type;
    private int authorID;

    public Book() {
    }

    public Book(int bookID, String bookName, String publicDate, String type, int authorID) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.publicDate = publicDate;
        this.type = type;
        this.authorID = authorID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    @NonNull
    @Override
    public String toString() {
        return bookID + " - " + bookName + " - " + publicDate + " - " + type;
    }
}

