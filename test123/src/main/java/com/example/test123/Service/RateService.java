package com.example.test123.Service;


import com.example.test123.Entity.Book;
import com.example.test123.Entity.Comments;
import com.example.test123.Entity.Rate;

import java.util.List;


public interface RateService {
    List<Rate> getRate(Book book);
    void saveRate(Rate rate);

    void deleteRateById(int id);
    Rate getRateById(int id);
}
