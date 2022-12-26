package com.example.test123.Controller;


import com.example.test123.Entity.Bill;
import com.example.test123.Entity.BillDetail;
import com.example.test123.Entity.Book;
import com.example.test123.Entity.User;
import com.example.test123.Service.BillDetailService;
import com.example.test123.Service.BillService;
import com.example.test123.Service.BookService;
import com.example.test123.Service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BillController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BillDetailService billDetailService;

    @Autowired
    private BillService billService;

    @Autowired
    private UserSerivce userSerivce;

    // getBill
    @GetMapping("/bookuser/bookbill")
    public String bill(Model model, HttpSession session){
        User user = userSerivce.findByIduser((String) session.getAttribute("user"));
        List<Bill> bill =  billService.getBills(user);
        List<Bill> bills =  new ArrayList<>();
        Bill a = new Bill();
        for(Bill x : bill){
            if(x.getCheckbill() == 1){
                bills.add(x);
            }
        }
        model.addAttribute("bills",bills);
        return "Bill/bill";
    }
    @GetMapping("/book/bookbill")
    public String billadmin(Model model, HttpSession session){
        List<Bill> bill =  billService.getAllBill();
        List<Bill> bills =  new ArrayList<>();
        Bill a = new Bill();
        for(Bill x : bill){
            if(x.getCheckbill() == 1){
                bills.add(x);
            }
        }
        model.addAttribute("bills",bills);
        return "Bill/bill";
    }

    @PostMapping ("/getabill/{id}")
    public String getbookcartadmin(@PathVariable(name = "id") int id, HttpSession session,Model model){
        List<Bill> bill =  billService.getAllBill();
        BillDetail billDetail = new BillDetail();
        Bill bill1 = new Bill();
        for(Bill x : bill){
            if(x.getBillid() == id){
                bill1 = x;
                break;
            }
        }
        List<BillDetail> billDetails = billDetailService.getBillDetail(bill1);
        model.addAttribute("bookcarts",billDetails);
        return "Bill/billbook";
    }

    // getabill
    @GetMapping("/getabill/{id}")
    public String getbookcart(@PathVariable(name = "id") int id, HttpSession session,Model model){
        User user = userSerivce.findByIduser((String) session.getAttribute("user"));
        List<Bill> bill =  billService.getBills(user);
        BillDetail billDetail = new BillDetail();
        Bill bill1 = new Bill();
        for(Bill x : bill){
            if(x.getBillid() == id){
                bill1 = x;
                break;
            }
        }
        List<BillDetail> billDetails = billDetailService.getBillDetail(bill1);
       model.addAttribute("bookcarts",billDetails);
       return "Bill/billbook";
    }

    //deletebill
    @DeleteMapping("/deleteabill/{id}")
    public String deleteabill(Model model, @PathVariable(value = "id") int id, HttpSession session) {
        if (session.getAttribute("user") != null) {
            billService.deleteBill(id);
            return "redirect:/bookuser/bookbill";
        }else{
            billService.deleteBill(id);
            return "redirect:/book/bookbill";
        }
    }
}
