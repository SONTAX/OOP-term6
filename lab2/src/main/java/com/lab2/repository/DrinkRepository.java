package com.lab2.repository;

import com.lab2.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Integer> {
  List<Drink> findAllByAmountIsGreaterThan(Integer amount);

  Optional<Drink> findAllByAmountIsGreaterThanAndId(Integer id, Integer amount);

  List<Drink> findAllById(Integer id);
}
