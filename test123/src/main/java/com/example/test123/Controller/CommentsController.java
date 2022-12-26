package com.example.test123.Controller;


import com.example.test123.Entity.*;
import com.example.test123.Service.BookService;
import com.example.test123.Service.CommentsService;
import com.example.test123.Service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class CommentsController {

    @Autowired
    private CommentsService commentsService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserSerivce userService;

    @PostMapping("/bookuser/detail/comments/{id}")
    public String signUp(Model model, @PathVariable(value = "id") int id, HttpSession session, @RequestParam("comment") String comment) {
        int temp1 = id;
         Book book = bookService.getBookById(temp1);
        String temp2 = (String) session.getAttribute("user");
        User user = userService.findByIduser(temp2);
        Comments abc = new Comments();
        abc.setBook(book);
        abc.setUser(user);
        abc.setContent(comment);
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("hh:mm:ss MM/dd/yyyy");
         String date1= DateFor.format(date);

        abc.setTimecreate(date1);
        commentsService.saveComments(abc);
        return "redirect:/bookuser/detail/" + id;
    }

    @GetMapping("/deletecomment/{id}")
    public String Commentdemove(Model model, @PathVariable(value = "id") int id, HttpSession session) {
        User user = userService.findByIduser((String) session.getAttribute("user"));
        Book book = bookService.getBookById(commentsService.getCommentById(id).getBook().getBookcode());
        if(commentsService.getCommentById(id).getUser().getEmail().equals(user.getEmail())){
            commentsService.deleteComments(id);
            return "redirect:/bookuser/detail/" + book.getBookcode();
        }else{
            return "redirect:/bookuser/detail/" + book.getBookcode();
        }
    }

}
