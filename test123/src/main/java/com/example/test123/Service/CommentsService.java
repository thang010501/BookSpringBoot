package com.example.test123.Service;


import com.example.test123.Entity.Book;
import com.example.test123.Entity.Comments;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

public interface CommentsService {
    List<Comments> getComments(Book book);
    void saveComments(Comments comments);
    void deleteComments(int id);
    Comments getCommentById(int id);
}
