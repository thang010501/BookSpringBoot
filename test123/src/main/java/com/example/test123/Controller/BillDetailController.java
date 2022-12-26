package com.example.test123.Controller;


import com.example.test123.Entity.*;
import com.example.test123.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class BillDetailController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BillDetailService billDetailService;

    @Autowired
    private BillService billService;

    @Autowired
    private UserSerivce userSerivce;


    // getBillCart
    @GetMapping("/bookuser/bookcart")
    public String bookdcart(Model model, HttpSession session){
        User user = userSerivce.findByIduser((String) session.getAttribute("user"));
        List<Bill> bill =  billService.getBills(user);
        Bill a = new Bill();
        for(Bill x : bill){
            if(x.getCheckbill() == 0){
                a = x;
                break;
            }
        }
        List<BillDetail> billDetails = billDetailService.getBillDetail(a);
        if(billDetails.size() != 0){
            model.addAttribute("bookcarts",billDetails);
        }
        model.addAttribute("bill",a.getBillid());
        return "Bill/billcart";
    }

    // add book cart
    @GetMapping("/bookuser/addbookcart/{id}")
    public String addcount(@PathVariable(value = "id") int id, Model model , HttpSession session) {
        if(session.getAttribute("user") == null){
            return "redirect:/loginuser";
        }
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "Bill/bookcount";
    }

    @PostMapping("/addbookcart/{id}")
    public String addbookcart(@PathVariable(name = "id") int id,HttpSession session, @RequestParam("amount") int amount){
        User user = userSerivce.findByIduser((String) session.getAttribute("user"));
        List<Bill> bill =  billService.getBills(user);
        Book book = bookService.getBookById(id);
        Bill a = new Bill();
        for(Bill x : bill){
            if(x.getCheckbill() == 0){
                a = x;
                break;
            }
        }
        BillDetail abc = new BillDetail();
        List<BillDetail> books = billDetailService.getBillDetail(a);
        boolean temp = true;
        for(BillDetail x : books){
            if(x.getBook().getBookcode() == id){
                abc = x;
                temp = false;
            }
        }
        if(temp == true) {
            BillDetail billDetail = new BillDetail();
            billDetail.setBill(a);
            billDetail.setBook(book);
            billDetail.setSoluong(amount);
            billDetailService.saveBillDetail(billDetail);
            return "redirect:/bookuser/bookcart";
        } else {
            abc.setSoluong(abc.getSoluong() + amount);
            billDetailService.saveBillDetail(abc);
            return "redirect:/bookuser/bookcart";
        }
    }

    @DeleteMapping("/addbookcart/{id}")
    public String Billdetaildemove(Model model, @PathVariable(value = "id") int id, HttpSession session) {
        User user = userSerivce.findByIduser((String) session.getAttribute("user"));
        List<Bill> bill =  billService.getBills(user);
        Book book = bookService.getBookById(id);
        Bill a = new Bill();
        for(Bill x : bill){
            if(x.getCheckbill() == 0){
                a = x;
                break;
            }
        }
        BillDetail abc = new BillDetail();
        List<BillDetail> books = billDetailService.getBillDetail(a);
        for(BillDetail x : books){
            if(x.getBook().getBookcode() == id){
                abc = x;
            }
        }
        billDetailService.deleteBillDetailById(abc.getBilldetailid());
        return "redirect:/bookuser/bookcart";
    }

    @PostMapping("/orderbill")
    public String orderbill(HttpSession session, Model model){
        User user = userSerivce.findByIduser((String) session.getAttribute("user"));
        List<Bill> bill =  billService.getBills(user);
        Bill a = new Bill();
        for(Bill x : bill){
            if(x.getCheckbill() == 0){
                a = x;
                break;
            }
        }
        if(a.getBillDetails().size() == 0){
            return "redirect:/bookuser/bookcart";
        }
        a.setCheckbill(1);
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("hh:mm:ss MM/dd/yyyy");
        String date1= DateFor.format(date);
        a.setTimebill(date1);
        billService.saveBill(a);
        Bill b = new Bill();
        b.setCheckbill(0);
        b.setUser(user);
        billService.saveBill(b);
        return "redirect:/bookuser/bookcart";
    }




    // update amount

    // add book cart
    @GetMapping("/bookuser/updateamount/{id}")
    public String updateamount(@PathVariable(value = "id") int id, Model model , HttpSession session) {
        if(session.getAttribute("user") == null){
            return "redirect:/loginuser";
        }
        BillDetail billDetail = billDetailService.getBillDetailById(id);
        model.addAttribute("billDetail", billDetail);
        return "Bill/billamount";
    }

    @PostMapping("/updateamount/{id}")
    public String updateamount(@PathVariable(name = "id") int id,HttpSession session, @RequestParam("amount") int amount){
        User user = userSerivce.findByIduser((String) session.getAttribute("user"));
        List<Bill> bill =  billService.getBills(user);
        Book book = bookService.getBookById(id);
        Bill a = new Bill();
        for(Bill x : bill){
            if(x.getCheckbill() == 0){
                a = x;
                break;
            }
        }
        BillDetail abc = new BillDetail();
        List<BillDetail> books = billDetailService.getBillDetail(a);
        for(BillDetail x : books){
            if(x.getBilldetailid() == id){
                abc = x;
            }
        }
        System.out.println(abc.getBilldetailid());
        abc.setSoluong(amount);
        billDetailService.saveBillDetail(abc);
        return "redirect:/bookuser/bookcart";
        }

}
