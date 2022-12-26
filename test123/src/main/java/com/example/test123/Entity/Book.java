package com.example.test123.Entity;


import javax.persistence.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookcode")
    private int bookcode;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "title cannot be blank")
    private String title;

    @Column(nullable = false)
    @NotEmpty(message = "author cannot be blank")
    private String author;

    @Column(nullable = false)
//    @NotEmpty(message = "category cannot be blank")
    private String category;

    @Column(nullable = false)
    @NotEmpty(message = "date cannot be blank")
    private String releasedate;

    @Column(nullable = false)
    @NotEmpty(message = "page cannot be blank")
//    @Pattern(regexp = "[0-9]+", message = "Only numbers allow")
    private String page;

    @Column(nullable = false)
    @NotEmpty(message = "describe cannot be blank")
    private String mota;
    @Column(nullable = true, length = 45)
    private String photo;

    @OneToMany(mappedBy = "book" , cascade = CascadeType.REMOVE)
    private List<Comments> comments;

    @OneToMany(mappedBy = "book" , cascade = CascadeType.REMOVE)
    private List<Rate> rates;

    @OneToMany(mappedBy = "book" , cascade = CascadeType.REMOVE , orphanRemoval = true)
    private List<BillDetail> billDetails;

    public Book() {}

    public Book(int bookcode, String title, String author, String category, String releasedate, String page, String mota, String photo, List<Comments> comments, List<Rate> rates, List<BillDetail> billDetails) {
        this.bookcode = bookcode;
        this.title = title;
        this.author = author;
        this.category = category;
        this.releasedate = releasedate;
        this.page = page;
        this.mota = mota;
        this.photo = photo;
        this.comments = comments;
        this.rates = rates;
        this.billDetails = billDetails;
    }

    public int getBookcode() {
        return bookcode;
    }

    public void setBookcode(int bookcode) {
        this.bookcode = bookcode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public List<BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }
}
