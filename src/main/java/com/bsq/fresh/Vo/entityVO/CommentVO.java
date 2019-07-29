package com.bsq.fresh.Vo.entityVO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentVO {

    @JsonProperty("commentId")
    private long commentId;

    @JsonProperty("commentDesc")
    private String commentDesc;

    @JsonProperty("commentScore")
    private long commentScore;

    @JsonProperty("categoryTwoFoodId")
    private long categoryTwoFoodId;

    @JsonProperty("time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date commentTime;

    @JsonProperty("userList")
    private List<UserVO> userList;
}
