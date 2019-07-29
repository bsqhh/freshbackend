package com.bsq.fresh.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class FoodSpecifications {

  @Id
  private long foodSpecificationsId;
  private String foodSpecificationsDesc;
  private long categoryTwoFoodId;
  private double categoryTwoFoodPrice;

}
