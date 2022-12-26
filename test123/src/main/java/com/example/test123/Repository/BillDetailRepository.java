package com.example.test123.Repository;


import com.example.test123.Entity.Bill;
import com.example.test123.Entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Integer> {
}
