package com.lab2.repository;

import com.lab2.model.Fill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FillRepository extends JpaRepository<Fill, Integer> {
  List<Fill> findAllByUserId(String userId);
}
