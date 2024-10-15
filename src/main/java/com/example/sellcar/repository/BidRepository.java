package com.example.sellcar.repository;

import com.example.sellcar.dto.BidDTO;
import com.example.sellcar.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid,Long> {


    List<Bid> findAllByUserId(Long userId);
}
