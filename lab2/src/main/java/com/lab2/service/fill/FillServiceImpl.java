package com.lab2.service.fill;

import com.lab2.model.Fill;
import com.lab2.repository.DrinkRepository;
import com.lab2.repository.FillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class FillServiceImpl implements FillService {

  private final FillRepository fillRepository;
  private final DrinkRepository drinkRepository;

  @Override
  public List<Fill> fills(String userId) {
    return isNull(userId) ? fillRepository.findAll() : fillRepository.findAllByUserId(userId);
  }

  @Override
  public void fill(Fill fill) {
    final var drink = fill.getDrink();
    drink.setAmount(drink.getAmount() + fill.getAmount());
    drinkRepository.saveAndFlush(drink);
    fillRepository.saveAndFlush(fill);
  }
}
