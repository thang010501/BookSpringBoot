package com.example.test123.Entity;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billid;

    private String timebill;

    private int checkbill;

    @OneToMany(mappedBy = "bill" , cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<BillDetail> billDetails;

    @ManyToOne
    @JoinColumn(name="email")
    private User user;

    public Bill() {
    }

    public Bill(int billid, String timebill, int checkbill, List<BillDetail> billDetails, User user) {
        this.billid = billid;
        this.timebill = timebill;
        this.checkbill = checkbill;
        this.billDetails = billDetails;
        this.user = user;
    }

    public int getBillid() {
        return billid;
    }

    public void setBillid(int billid) {
        this.billid = billid;
    }

    public String getTimebill() {
        return timebill;
    }

    public void setTimebill(String timebill) {
        this.timebill = timebill;
    }

    public int getCheckbill() {
        return checkbill;
    }

    public void setCheckbill(int checkbill) {
        this.checkbill = checkbill;
    }

    public List<BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
