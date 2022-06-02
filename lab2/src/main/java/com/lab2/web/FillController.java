package com.lab2.web;

import com.lab2.service.fill.FillService;
import com.lab2.web.converter.FillConverter;
import com.lab2.web.dto.FillDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FillController implements UserInformationSecurityContextHolder {
  private final FillService service;
  private final FillConverter converter;

  @GetMapping("/fills")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  public List<FillDto> fills(Principal principal) {
    String userId = null;
    if (getRoles().contains("USER")) {
      userId = principal.getName();
    }
    return service.fills(userId).stream().map(converter::toDto).toList();
  }

  @GetMapping("/fill")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public void fill(FillDto fillDto) {
    log.error("{}", fillDto);
    final var fill = converter.toModel(fillDto);
    log.error("{}", fill);
    service.fill(fill);
  }
}
