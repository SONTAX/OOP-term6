package com.lab2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bill")
public class Bill {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Integer id;

  @Column(name = "user_id", nullable = false)
  private String userId;

  @ManyToOne
  @JoinColumn(name = "drink_id", referencedColumnName = "id", nullable = false)
  private Drink drink;

  @Column(name = "amount", nullable = false)
  private int amount;

  @Column(name = "cost", nullable = false)
  private int cost;

  @Column(name = "paid", nullable = false)
  private boolean paid;
}
