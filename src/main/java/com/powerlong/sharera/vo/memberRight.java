package com.powerlong.sharera.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class memberRight {
    private String memberLevel;
    private String memberLevelName;
    private Integer consumePoint;
    private Integer exchangeMoney;
    private Integer consumeMoney;
    private Integer giftPoint;
    private Boolean isGiftPoint;
}
