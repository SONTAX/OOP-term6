package com.lab2.repository;

import com.lab2.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

  List<Bill> findAllByUserId(String clientId);

  Optional<Bill> findAllByUserIdAndDrinkIdAndPaidIsTrue(String userId, Integer drinkId);

  Optional<Bill> findAllByUserIdAndDrinkIdAndPaidIsFalse(String userId, Integer drinkId);
}
