package com.lab2.web;

import com.lab2.model.Bill;
import com.lab2.model.Drink;
import com.lab2.service.bill.BillService;
import com.lab2.web.converter.BillConverter;
import com.lab2.web.dto.BillDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController implements UserInformationSecurityContextHolder {
  private final BillService service;
  private final BillConverter converter;

  @GetMapping
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  public List<BillDto> bills(Principal principal) {
    String userId = null;
    if (getRoles().contains("USER")) {
      userId = principal.getName();
    }
    return service.bills(userId).stream().map(converter::toDto).toList();
  }

  @GetMapping("/drink")
  @PreAuthorize("hasRole('USER')")
  public void buy(BillDto billDto, Principal principal) {
    final var bill = converter.toModel(billDto);
    bill.setUserId(principal.getName());
    service.buyDrink(bill);
  }

  @GetMapping("/drink/pay")
  @PreAuthorize("hasRole('USER')")
  public void pay(BillDto billDto, Principal principal) {
    final var bill = Bill.builder().drink(Drink.builder().id(billDto.getDrinkId()).build()).userId(principal.getName()).build();
    service.payDrink(bill);
  }
}
