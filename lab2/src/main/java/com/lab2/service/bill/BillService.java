package com.lab2.service.bill;

import com.lab2.model.Bill;

import java.util.List;

public interface BillService {
  List<Bill> bills(String userId);

  void buyDrink(Bill bill);

  void payDrink(Bill bill);
}
