package com.example.test123.Controller;


import com.example.test123.Entity.Book;
import com.example.test123.Entity.Comments;
import com.example.test123.Entity.Rate;
import com.example.test123.Entity.User;
import com.example.test123.Service.BookService;
import com.example.test123.Service.CommentsService;
import com.example.test123.Service.RateService;
import com.example.test123.Service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class RateController {
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserSerivce userService;
    @Autowired
    private RateService rateService;

    @PostMapping("/bookuser/detail/rates/{id}")
    public String rateremove(Model model, @PathVariable(value = "id") int id, HttpSession session, @RequestParam("rating") String rate) {
        int temp1 = id;
        Book book = bookService.getBookById(temp1);
        String temp2 = (String) session.getAttribute("user");
        User user = userService.findByIduser(temp2);
        List<Rate> rates = rateService.getRate(book);
        for(Rate x : rates){
            if(x.getUser().getEmail().equals(temp2)){
                rateService.deleteRateById(x.getRateid());
            }
        }
        Rate abc = new Rate();
        abc.setBook(book);
        abc.setUser(user);
        abc.setRate(Integer.parseInt(rate));
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("hh:mm:ss MM/dd/yyyy");
        String date1= DateFor.format(date);
        String imgrate = "rating"+rate+".png";
        abc.setImgrate(imgrate);
        abc.setTimerate(date1);
        rateService.saveRate(abc);
        return "redirect:/bookuser/detail/" + id;
    }
    @GetMapping("/deleterate/{id}")
    public String Ratedemove(Model model, @PathVariable(value = "id") int id, HttpSession session) {
        User user = userService.findByIduser((String) session.getAttribute("user"));
        Book book = bookService.getBookById(rateService.getRateById(id).getBook().getBookcode());
        if(rateService.getRateById(id).getUser().getEmail().equals(user.getEmail())){
            rateService.deleteRateById(id);
            return "redirect:/bookuser/detail/" + book.getBookcode();
        }else{
            return "redirect:/bookuser/detail/" + book.getBookcode();
        }
    }
}
