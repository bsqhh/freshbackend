package com.bsq.fresh.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Category {

  @Id
  private long categoryId;
  private String categoryName;

}
