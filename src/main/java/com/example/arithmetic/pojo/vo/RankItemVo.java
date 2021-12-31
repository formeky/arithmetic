package com.example.arithmetic.pojo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ASUS
 */
@Data
@NoArgsConstructor
public class RankItemVo {
    private Integer ownerId;
    private String name;
    private Integer correct;
    private Integer problemCount;
    private Integer rank;
}
