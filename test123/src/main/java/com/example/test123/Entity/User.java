package com.example.test123.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    //    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name ="email")
    @NotEmpty(message = "email cannot be blank")
    private String email;

    @NotEmpty(message = "password cannot be blank")
    private String password;

    @Column(name = "first_name", nullable = false, length = 20)
    @NotEmpty(message = "firstName cannot be blank")
    private String firstName;

    @NotEmpty(message = "last_name cannot be blank")
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "role")
    private int role;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.REMOVE)
    private List<Comments> comments;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.REMOVE)
    private List<Rate> rates;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.REMOVE)
    private List<Bill> bills;

    public User() {}

    public User(String email, String password, String firstName, String lastName, int role, List<Comments> comments, List<Rate> rates, List<Bill> bills) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.comments = comments;
        this.rates = rates;
        this.bills = bills;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}
