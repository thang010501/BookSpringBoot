package com.example.test123.Controller;

import com.example.test123.Entity.Book;
import com.example.test123.Entity.Comments;
import com.example.test123.Entity.Rate;
import com.example.test123.Entity.User;
import com.example.test123.Repository.UserRepository;
import com.example.test123.Service.BookService;
import com.example.test123.Service.CommentsService;
import com.example.test123.Service.RateService;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;


//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;



@Controller
public class AppController {

    private int tmpId = 0;
    @Autowired
    private BookService bookService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private RateService rateService;

    //Books Controller --


    // Book get all
    @GetMapping("/books")
    public String showListBook(Model model) {
        model.addAttribute("books", bookService.getAllBook());
        return "books";
    }

    // Add Book
    @PostMapping("/edit/{id}")
    public String addBook(@PathVariable(value = "id") int id , @ModelAttribute("book") @Valid Book book, BindingResult result,
                          @RequestParam("image") MultipartFile image,@RequestParam("abc") String temp, ModelMap model ) throws IOException {

        book.setCategory(temp);
        if (result.hasErrors()) {
                return "BookEdited";
        }
//            Book booktemp = bookService.getBookById(id);

            if (!bookService.checkTitle(book.getTitle())) {
                String error = "Title = " + book.getTitle() + " exists in the database";
                model.addAttribute("error", error);
                model.addAttribute("book", book);
                return "BookEdited";
            }
            Path path = Paths.get("uploads/");
            try {
                InputStream inputStream = image.getInputStream();
                Files.copy(inputStream, path.resolve(image.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                book.setPhoto(image.getOriginalFilename().toLowerCase());
                model.addAttribute("book", book);
            } catch (Exception e) {
                e.printStackTrace();
            }
            bookService.saveBook(book);
            return "redirect:/books";
    }

    // Update book
    @GetMapping("/book/editBook/{id}")
    public String formViewBook(@PathVariable(value = "id") int id, Model model, HttpSession session) {
        if(session.getAttribute("username") == null){
            return "redirect:/loginuser";
        }
        tmpId = id;
        if (tmpId == -1) {
            Book book = new Book();
            book.setBookcode(0);
            model.addAttribute("book", book);
            return "BookEdited";
        } else {
            Book book = bookService.getBookById(tmpId);
            model.addAttribute("book", book);
            return "BookEdited";
        }
    }
    @PutMapping("/edit/{id}")
    public String updateBook(@PathVariable(value = "id") int id,
                             @ModelAttribute("book") @Valid Book book,
                             BindingResult result,
                             @RequestParam("image") MultipartFile image, @RequestParam("abc") String temp,@RequestParam("deleteimage") String delete, ModelMap model) {
        book.setCategory(temp);
        Book booktemp = bookService.getBookById(id);
        if (result.hasErrors()) {
            book.setPhoto(booktemp.getPhoto());
            book.setBookcode(id);
            model.addAttribute("book", book);
            return "BookEdited";
        }
        if(!book.getTitle().equals(booktemp.getTitle())) {
            if (!bookService.checkTitle(book.getTitle())) {
                String error = "Title = " + book.getTitle() + " exists in the database";
                model.addAttribute("error", error);
                book.setPhoto(booktemp.getPhoto());
                book.setBookcode(id);
                model.addAttribute("book", book);
                return "BookEdited";
            }
        }
        Path path = Paths.get("uploads/");
        try {
            InputStream inputStream = image.getInputStream();
            Files.copy(inputStream, path.resolve(image.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            book.setPhoto(image.getOriginalFilename().toLowerCase());
            model.addAttribute("book",book);
        }catch(Exception e) {
            e.printStackTrace();
        }
        if(book.getPhoto() == null){
            book.setPhoto(booktemp.getPhoto());
        }
        if(delete.equals("a")){
            book.setPhoto("");
        }
        book.setBookcode(id);
        bookService.updateBook(id, book);
        return "redirect:/books";
    }


    // Delete book
    @GetMapping("/book/delete/{id}")
    public String formDeleteBook(@PathVariable(value = "id") int id, Model model , HttpSession session) {
        if(session.getAttribute("username") == null){
            return "redirect:/loginuser";
        }
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "BookDeleted";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable(name = "id") int id){
        bookService.deleteBookById(id);
        return "redirect:/books";
    }








    // Book User

    // getBook user
    @GetMapping("/bookuser")
    public String bookuser(Model model){
        String abc = "error.png";
        model.addAttribute("books", bookService.getAllBook());
        model.addAttribute("error",abc);
        return "BookUser/bookuser";
    }

    // getBook detail
    @GetMapping("/bookuser/detail/{id}")
    public String bookdetail(@PathVariable(value = "id") int id, Model model){
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
            model.addAttribute("comments", commentsService.getComments(book));
            model.addAttribute("rates", rateService.getRate(book));
            List<Rate> a = rateService.getRate(book);
            if(a.size() != 0) {
                int countrate = 0;
                for (Rate x : a) {
                    int temp = x.getRate();
                    countrate += temp;
                }
                countrate = countrate / a.size();
                String imgratetb = "rating" + countrate + ".png";
                model.addAttribute("tbrate", imgratetb);
            }
        return "BookUser/bookdetail";
    }

}
