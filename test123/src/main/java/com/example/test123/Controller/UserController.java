package com.example.test123.Controller;

import javax.servlet.http.HttpSession;

import com.example.test123.Entity.Bill;
import com.example.test123.Entity.User;
import com.example.test123.Service.BillService;
import com.example.test123.Service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserSerivce userSerivce;
    @Autowired
    private BillService billService;

    // hiển thị form đăng ký
    @GetMapping("/register")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "LoginView/signup_form";
    }

    // lưu đăng ký
    @PostMapping("/process_register")
    public String register(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "LoginView/signup_form";
        }
        Optional<User> temp = userSerivce.findById(user.getEmail());
        if( temp.isPresent()){
            String error = "email = "+ user.getEmail() + " exists in the database";
            model.addAttribute("error", error);
            return "LoginView/signup_form";
        }
        userSerivce.save(user);
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setCheckbill(0);
        billService.saveBill(bill);
        return "redirect:/loginuser";
    }

    //hiển thị form đăng nhập
    @GetMapping("/loginuser")
    public String login(Model model, HttpSession session){
        if(session.getAttribute("user") != null ){
            return "redirect:/bookuser";
        }
        if(session.getAttribute("username") != null){
            return "redirect:/books";
        }
        model.addAttribute("user", new User());
        return "LoginView/loginuser";
    }

    // check đăng nhập
    @PostMapping("/abc")
    public String checkLogin(User user , Model  model, HttpSession session) {
        if(userSerivce.checkLogin(user.getEmail(), user.getPassword()) != null)
        {
            user = userSerivce.checkLogin(user.getEmail(), user.getPassword());
            if(user.getRole() == 1) {
                session.setAttribute("username", user.getEmail());
                return "redirect:/books";
            }
            else{
                session.setAttribute("user", user.getEmail());
                return "redirect:/bookuser";
            }
        }
        else
        {
            model.addAttribute("error", "User or/and password is wrong");
        }
        return "LoginView/loginuser";

    }

    // logout session
    @GetMapping("/log-out")
    public String logOut(Model model, HttpSession session) {
        if(session.getAttribute("username") != null) {
            session.removeAttribute("username");
            return "redirect:/books";
        }else{
            session.removeAttribute("user");
            return "redirect:/bookuser";
        }
    }



}
