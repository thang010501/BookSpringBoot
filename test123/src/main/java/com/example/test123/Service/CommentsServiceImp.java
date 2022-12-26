package com.example.test123.Service;

import com.example.test123.Entity.BillDetail;
import com.example.test123.Entity.Book;
import com.example.test123.Entity.Comments;
import com.example.test123.Repository.BookRepository;
import com.example.test123.Repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CommentsServiceImp implements CommentsService{

    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private BookRepository bookRepository;

    public List<Comments> getComments(Book book) {
        Book queryResult =  bookRepository.findById(book.getBookcode()).get();
//        System.out.println(queryResult.getComments().get(1).getUser().getEmail());
        List<Comments> comments =  queryResult.getComments();
        return comments;
    }
    public void saveComments(Comments comments) {
        commentsRepository.save(comments);
    }

    public void deleteComments(int id) {
        commentsRepository.deleteById(id);
    }

    @Override
    public Comments getCommentById(int id) {

        return commentsRepository.findById(id).get();
    }
}
