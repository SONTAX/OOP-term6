package com.lab2.service.drink;

import com.lab2.model.Drink;
import com.lab2.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {
  private final DrinkRepository drinkRepository;

  @Override
  public List<Drink> getDrinks(Boolean isAdmin) {
    return Boolean.TRUE.equals(isAdmin) ? drinkRepository.findAll() : drinkRepository.findAllByAmountIsGreaterThan(0);
  }
}
