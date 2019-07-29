package com.bsq.fresh.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {

  @Id
  private long userId;
  private String userUsername;
  private String userPassword;
  private String userPhone;
  private String userHeaderImg;

}
