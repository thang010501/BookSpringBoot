package com.example.test123.Service;

import com.example.test123.Entity.Bill;
import com.example.test123.Entity.Book;
import com.example.test123.Entity.Comments;
import com.example.test123.Entity.User;

import java.util.List;

public interface BillService {
    List<Bill> getBills(User user);
    void saveBill(Bill bill);
    void deleteBill(int id);
    List<Bill> getAllBill();
}
