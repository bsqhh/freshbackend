package com.bsq.fresh.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class CategoryTwoFood {

  @Id
  private long categoryTwoFoodId;
  private String categoryTwoFoodImage;
  private String categoryTwoFoodDesc;
  private BigDecimal categoryTwoFoodPrice;
  private double categoryTwoFoodOriginPrice;
  private long categoryTwoId;
  private long categoryTwoFoodStock;


}
