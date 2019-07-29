package com.bsq.fresh.Vo.entityVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVO {
    @JsonProperty("categoryId")
    private long categoryId;

    @JsonProperty("categoryTitle")
    private String categoryName;

    @JsonProperty("lists")
    private List<CategoryTwoVO> categoryTwoVOList;

}
