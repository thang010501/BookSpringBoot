package com.example.test123.Entity;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "billdetail")
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billdetailid;

    private int soluong;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="bookcode")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="billid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Bill bill;

    public BillDetail() {
    }

    public BillDetail(int billdetailid, int soluong, Book book, Bill bill) {
        this.billdetailid = billdetailid;
        this.soluong = soluong;
        this.book = book;
        this.bill = bill;
    }

    public int getBilldetailid() {
        return billdetailid;
    }

    public void setBilldetailid(int billdetailid) {
        this.billdetailid = billdetailid;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
