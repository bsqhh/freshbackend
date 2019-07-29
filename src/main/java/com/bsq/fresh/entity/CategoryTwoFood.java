package com.bsq.fresh.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class CategoryTwoFood {

  @Id
  private long categoryTwoFoodId;
  private String categoryTwoFoodImage;
  private String categoryTwoFoodDesc;
  private double categoryTwoFoodPrice;
  private double categoryTwoFoodOriginPrice;
  private long categoryTwoId;
  private long categoryTwoFoodStock;

}
