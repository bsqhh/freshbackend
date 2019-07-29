package com.bsq.fresh.Vo.entityVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CategoryTwoVO {

    @JsonProperty("id")
    private long categoryTwoId;

    @JsonProperty("title")
    private String categoryTwoName;

    @JsonProperty("pic_url")
    private String categoryTwoImage;

    @JsonProperty("categoryTwoFoodVOList")
    private List<CategoryTwoFoodVO> categoryTwoFoodVOList;
}
