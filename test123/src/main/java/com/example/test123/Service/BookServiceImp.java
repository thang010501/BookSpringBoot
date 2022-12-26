package com.example.test123.Service;

import com.example.test123.Entity.Book;
import com.example.test123.Entity.Comments;
import com.example.test123.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book getBookById(int id) {
        Optional<Book> temp = bookRepository.findById(id);
        Book book = null;
        if(temp.isPresent()) {
            book = temp.get();
        }
        else {
            return null;
        }
        return book;
    }

    @Override
    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void updateBook(int id, Book book) {
        Optional<Book> optional = bookRepository.findById(id);
        Book bookInDB = null;
        if(optional.isPresent()) {
            bookInDB = optional.get();
            bookInDB.setTitle(book.getTitle());
            bookInDB.setAuthor(book.getAuthor());
            bookInDB.setCategory(book.getCategory());
            bookInDB.setReleasedate(book.getReleasedate());
            bookInDB.setPage(book.getPage());
            bookInDB.setPhoto(book.getPhoto());
            bookInDB.setMota(book.getMota());
        }
        else {
            throw new RuntimeException("Book not found with ID: " + id);
        }
        System.out.println("chạy đến lưu");
        bookRepository.save(bookInDB);
    }

    @Override
    public boolean checkTitle(String title) {
        List<Book> a = null;
        a = bookRepository.findByTitle(title);
        System.out.println("dvt1");
        System.out.println(a.size());
        if (a.size() < 1){
            System.out.println("dvt2");
            return true;
        }
        System.out.println("dvt3");
        return false;
    }
}

