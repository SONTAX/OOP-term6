package com.lab2.service.drink;

import com.lab2.model.Drink;

import java.util.List;

public interface DrinkService {

  List<Drink> getDrinks(Boolean isAdmin);
}
