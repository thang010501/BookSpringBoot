package com.example.test123.Service;


import com.example.test123.Entity.Bill;
import com.example.test123.Entity.Book;
import com.example.test123.Entity.Comments;
import com.example.test123.Entity.User;
import com.example.test123.Repository.BillRepository;
import com.example.test123.Repository.BookRepository;
import com.example.test123.Repository.CommentsRepository;
import com.example.test123.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImp implements BillService{
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Bill> getBills(User user) {
        User queryResult =  userRepository.findById(user.getEmail()).get();
//        System.out.println(queryResult.getComments().get(1).getUser().getEmail());
        List<Bill> bills =  queryResult.getBills();
        return bills;
    }
    public void saveBill(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public List<Bill> getAllBill() {
        return billRepository.findAll();
    }
    public void deleteBill(int id) {
        System.out.println(id);
        billRepository.deleteById(id);
    }
}
