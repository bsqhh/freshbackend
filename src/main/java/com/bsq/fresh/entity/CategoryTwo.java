package com.bsq.fresh.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class CategoryTwo {

  @Id
  private long categoryTwoId;
  private String categoryTwoName;
  private String categoryTwoImage;
  private long categoryId;

}
