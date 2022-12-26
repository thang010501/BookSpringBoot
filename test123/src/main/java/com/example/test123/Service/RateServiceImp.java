package com.example.test123.Service;

import com.example.test123.Entity.Book;
import com.example.test123.Entity.Comments;
import com.example.test123.Entity.Rate;
import com.example.test123.Repository.BookRepository;
import com.example.test123.Repository.CommentsRepository;
import com.example.test123.Repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PreRemove;
import java.util.List;

@Service
public class RateServiceImp implements RateService {

    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private BookRepository bookRepository;

    public List<Rate> getRate(Book book) {
        Book queryResult =  bookRepository.findById(book.getBookcode()).get();
//        System.out.println(queryResult.getComments().get(1).getUser().getEmail());
        List<Rate> rates =  queryResult.getRates();
        return rates;
    }
    public void saveRate(Rate rate) { rateRepository.save(rate);}

    public void deleteRateById(int id) {
        System.out.println(id);
        rateRepository.deleteById(id);
        System.out.println("abc");
    }
    public Rate getRateById(int id) {

        return rateRepository.findById(id).get();
    }
}
