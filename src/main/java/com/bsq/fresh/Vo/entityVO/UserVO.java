package com.bsq.fresh.Vo.entityVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserVO {

    @JsonProperty("userId")
    private long userId;
    @JsonProperty("userUsername")
    private String userUsername;
    @JsonProperty("userPhone")
    private String userPhone;
    @JsonProperty("userHeaderImg")
    private String userHeaderImg;
}
