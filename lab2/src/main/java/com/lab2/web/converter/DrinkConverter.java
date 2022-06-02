package com.lab2.web.converter;


import com.lab2.model.Drink;
import com.lab2.web.dto.DrinkDto;
import org.springframework.stereotype.Component;

@Component
public class DrinkConverter implements Converter<Drink, DrinkDto> {

  @Override
  public DrinkDto toDto(Drink model) {
    return new DrinkDto(model.getId(), model.getName(), model.getAmount(), model.getPrice());
  }

  @Override
  public Drink toModel(DrinkDto dto) {
    return Drink.builder().name(dto.getName()).amount(dto.getAmount()).price(dto.getPrice()).build();
  }
}
