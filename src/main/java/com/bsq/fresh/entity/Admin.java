package com.bsq.fresh.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Admin {

  @Id
  private long adminId;
  private String adminUsername;
  private String adminPassword;
  private long adminPhone;

}
