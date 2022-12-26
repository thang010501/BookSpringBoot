package com.example.test123.Service;

import com.example.test123.Entity.Bill;
import com.example.test123.Entity.BillDetail;
import com.example.test123.Entity.Comments;
import com.example.test123.Entity.User;
import com.example.test123.Repository.BillDetailRepository;
import com.example.test123.Repository.BillRepository;
import com.example.test123.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillDetailServiceImp implements BillDetailService{
    @Autowired
    private BillDetailRepository billDetailRepository;
    @Autowired
    private BillRepository billRepository;

    public List<BillDetail> getBillDetail(Bill bill) {
        Bill queryResult =  billRepository.findById(bill.getBillid()).get();
//        System.out.println(queryResult.getComments().get(1).getUser().getEmail());
        List<BillDetail> billDetails =  queryResult.getBillDetails();
        return billDetails;
    }
    public void saveBillDetail(BillDetail billDetail) {
        billDetailRepository.save(billDetail);
    }

    public void deleteBillDetailById(int id) {
        System.out.println(id);
//        billDetailRepository.deleteAll(a);
        BillDetail a = billDetailRepository.findById(id).get();

        billDetailRepository.deleteById(id);
        System.out.println("abc");
    }

    public BillDetail getBillDetailById(int id) {

        return billDetailRepository.findById(id).get();
    }
}
