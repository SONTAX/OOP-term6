package com.lab2.service.bill;

import com.lab2.model.Bill;
import com.lab2.repository.BillRepository;
import com.lab2.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
  private final DrinkRepository drinkRepository;
  private final BillRepository billRepository;

  @Override
  public List<Bill> bills(String userId) {
    return isNull(userId) ? billRepository.findAll() : billRepository.findAllByUserId(userId);
  }

  @Override
  public void buyDrink(Bill bill) {
    final var drink = drinkRepository.findById(bill.getDrink().getId()).orElseThrow(() -> new EntityNotFoundException("Drink not found"));
    bill.setCost(bill.getAmount() * drink.getPrice());
    bill.setPaid(false);
    billRepository.saveAndFlush(bill);
  }

  @Override
  public void payDrink(Bill bill) {
    final var drink = drinkRepository.findAllByAmountIsGreaterThanAndId(bill.getDrink().getId(), 0).orElseThrow(() -> new EntityNotFoundException("Drink not found or runs out"));
    final var billDb = billRepository.findAllByUserIdAndDrinkIdAndPaidIsFalse(bill.getUserId(), bill.getDrink().getId()).orElseThrow(() -> new EntityNotFoundException("Bill not found or payed"));
    drink.setAmount(drink.getAmount() - bill.getDrink().getAmount());
    drinkRepository.saveAndFlush(drink);
    billDb.setPaid(true);
    billRepository.saveAndFlush(billDb);
  }
}
