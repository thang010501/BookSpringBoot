package com.example.test123.Repository;

import com.example.test123.Entity.Book;
import com.example.test123.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository  extends JpaRepository<Comments, Integer> {

}
