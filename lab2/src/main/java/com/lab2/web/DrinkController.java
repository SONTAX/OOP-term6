package com.lab2.web;

import com.lab2.service.drink.DrinkService;
import com.lab2.web.converter.DrinkConverter;
import com.lab2.web.dto.DrinkDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/drinks")
@RequiredArgsConstructor
public class DrinkController implements UserInformationSecurityContextHolder {
  private final DrinkService service;
  private final DrinkConverter converter;

  @GetMapping
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  public List<DrinkDto> getDrinks() {
    return service.getDrinks(getRoles().contains("ADMIN")).stream().map(converter::toDto).toList();
  }

}
