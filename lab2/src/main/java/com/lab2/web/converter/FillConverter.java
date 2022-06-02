package com.lab2.web.converter;


import com.lab2.model.Drink;
import com.lab2.model.Fill;
import com.lab2.web.dto.FillDto;
import org.springframework.stereotype.Component;

@Component
public class FillConverter implements Converter<Fill, FillDto> {

  @Override
  public FillDto toDto(Fill model) {
    return new FillDto(model.getUserId(), model.getDrink().getId(), model.getAmount());
  }

  @Override
  public Fill toModel(FillDto dto) {
    return Fill.builder().userId(dto.getUserId()).drink(Drink.builder().id(dto.getDrinkId()).build()).amount(dto.getAmount()).build();
  }
}
