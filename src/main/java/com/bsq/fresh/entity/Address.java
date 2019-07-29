package com.bsq.fresh.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Address {

  @Id
  private long addressId;
  private String addressDes;
  private long userId;

}
