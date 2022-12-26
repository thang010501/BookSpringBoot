package com.example.test123.Entity;


import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentid;

    private String content;

    private String timecreate;


    @ManyToOne
    @JoinColumn(name="bookcode")
    private Book book;

    @ManyToOne
    @JoinColumn(name="email")
    private User user;

    public Comments(int commentid, String content, String timecreate, Book book, User user) {
        this.commentid = commentid;
        this.content = content;
        this.timecreate = timecreate;
        this.book = book;
        this.user = user;
    }

    public Comments() {

    }

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimecreate() {
        return timecreate;
    }

    public void setTimecreate(String timecreate) {
        this.timecreate = timecreate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
