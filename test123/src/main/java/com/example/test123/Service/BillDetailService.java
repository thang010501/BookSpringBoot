package com.example.test123.Service;


import com.example.test123.Entity.Bill;
import com.example.test123.Entity.BillDetail;
import com.example.test123.Entity.User;

import java.util.List;

public interface BillDetailService {
    List<BillDetail> getBillDetail(Bill bill);
    void saveBillDetail(BillDetail billDetail);
    void deleteBillDetailById(int id);
    BillDetail getBillDetailById(int id);
}
