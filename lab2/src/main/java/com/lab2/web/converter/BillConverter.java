package com.lab2.web.converter;


import com.lab2.model.Bill;
import com.lab2.model.Drink;
import com.lab2.web.dto.BillDto;
import org.springframework.stereotype.Component;

@Component
public class BillConverter implements Converter<Bill, BillDto> {

  @Override
  public BillDto toDto(Bill model) {
    return new BillDto(model.getUserId(), model.getDrink().getId(), model.getAmount(), model.getCost(), model.isPaid());
  }

  @Override
  public Bill toModel(BillDto dto) {
    return Bill.builder().drink(Drink.builder().id(dto.getDrinkId()).build()).amount(dto.getAmount()).build();
  }
}
