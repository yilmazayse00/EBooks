package com.example.ebooks.model;

public class Book {
    private String bookId;
    private String bookName;
    private String authorName;
    private Integer bookImage;


    public Book(String bookId, String bookName, String authorName, Integer bookImage) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookImage = bookImage;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getBookImage() {
        return bookImage;
    }

    public void setBookImage(Integer bookImage) {
        this.bookImage = bookImage;
    }


    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
