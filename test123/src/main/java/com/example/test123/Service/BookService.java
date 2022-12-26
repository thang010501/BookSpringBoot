package com.example.test123.Service;

import com.example.test123.Entity.Book;
import com.example.test123.Entity.Comments;

import java.util.List;


public interface BookService {
    List<Book> getAllBook();
    void saveBook(Book book);
    Book getBookById(int id);
    void deleteBookById(int id);
    void updateBook(int id, Book book);

    boolean checkTitle(String title);
}