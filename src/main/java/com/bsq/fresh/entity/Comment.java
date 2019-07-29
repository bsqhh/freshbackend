package com.bsq.fresh.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
public class Comment {

  @Id
  private long commentId;
  private String commentDesc;
  private long commentScore;
  private long userId;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date commentTime;
  private long categoryTwoFoodId;

}
