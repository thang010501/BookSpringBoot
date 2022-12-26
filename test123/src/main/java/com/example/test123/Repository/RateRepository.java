package com.example.test123.Repository;

import com.example.test123.Entity.Comments;
import com.example.test123.Entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {

}
