package com.lab2.web.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder(toBuilder = true)
@Value(staticConstructor = "of")
@JsonInclude(NON_NULL)
public class FillDto {

  @NotBlank String userId;
  @NotNull Integer drinkId;
  Integer amount;

  @JsonCreator
  public FillDto(String userId, Integer drinkId, Integer amount) {
    this.userId = userId;
    this.drinkId = drinkId;
    this.amount = amount == null ? 0 : amount;
  }
}
