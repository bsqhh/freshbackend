package com.bsq.fresh.Vo.entityVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CategoryTwoFoodVO {

    @JsonProperty("id")
    private long categoryTwoFoodId;

    @JsonProperty("pic_url")
    private String categoryTwoFoodImage;

    @JsonProperty("title")
    private String categoryTwoFoodDesc;

    @JsonProperty("marketPrice")
    private BigDecimal categoryTwoFoodPrice;

    @JsonProperty("originPrice")
    private double categoryTwoFoodOriginPrice;

    @JsonProperty("num")
    private long categoryTwoFoodStock;

    @JsonProperty("comment")
    private int comment;

    @JsonProperty("rate")
    private double rate;

    @JsonProperty("commentList")
    private List<CommentVO> commentList;
}
