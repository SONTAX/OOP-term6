package com.lab2.web.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Value;

import javax.validation.groups.Default;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder(toBuilder = true)
@Value(staticConstructor = "of")
@JsonInclude(NON_NULL)
public class BillDto {
  @NotNull(groups = RejectGroup.class) String userId;
  @NotNull(groups = RejectGroup.class) Integer drinkId;
  @NotNull Integer amount;
  @Null Integer cost;
  @Null Boolean paid;

  @JsonCreator
  public BillDto(@NotNull(groups = RejectGroup.class) String userId, @NotNull(groups = RejectGroup.class) Integer drinkId,
                 @NotNull Integer amount, @Null Integer cost, @Null Boolean paid) {
    this.userId = userId;
    this.drinkId = drinkId;
    this.amount = amount;
    this.cost = cost;
    this.paid = paid;
  }

  public interface RejectGroup extends Default {

  }
}