package com.example.test123.Entity;


import javax.persistence.*;

@Entity
@Table(name = "rate")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rateid;

    private String timerate;

    private int rate;

    private String imgrate;

    @ManyToOne
    @JoinColumn(name="bookcode")
    private Book book;

    @ManyToOne
    @JoinColumn(name="email")
    private User user;

    public Rate() {
    }

    public Rate(int rateid, String timerate, int rate, String imgrate, Book book, User user) {
        this.rateid = rateid;
        this.timerate = timerate;
        this.rate = rate;
        this.imgrate = imgrate;
        this.book = book;
        this.user = user;
    }

    public int getRateid() {
        return rateid;
    }

    public void setRateid(int rateid) {
        this.rateid = rateid;
    }

    public String getTimerate() {
        return timerate;
    }

    public void setTimerate(String timerate) {
        this.timerate = timerate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getImgrate() {
        return imgrate;
    }

    public void setImgrate(String imgrate) {
        this.imgrate = imgrate;
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
