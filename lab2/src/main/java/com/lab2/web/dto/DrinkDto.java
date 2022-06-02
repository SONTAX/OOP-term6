package com.lab2.web.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Value;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder(toBuilder = true)
@Value(staticConstructor = "of")
@JsonInclude(NON_NULL)
public class DrinkDto {
  @Null Integer id;
  @NotBlank @NotNull String name;
  @Null Integer amount;
  @NotNull Integer price;

  @JsonCreator
  public DrinkDto(@Null Integer id, @NotBlank @NotNull String name,
                  @Null Integer amount, @NotNull Integer price) {
    this.id = id;
    this.name = name;
    this.amount = amount;
    this.price = price;
  }
}
