package com.powerlong.sharera.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class useMessageVo {
    private String projectId;
    private String useTitle;
    private String useContent;

}
