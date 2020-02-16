package com.powerlong.sharera.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class checkResVo {
    private String id;
    private String status;
    private String nextAutor;
    private String autor;
    private String backReason;
}
