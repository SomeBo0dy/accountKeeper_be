package pers.xyj.modules.accountKeeper.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeNameAndCountVo {
    private Integer typeId;
    private String typeName;
    private Integer count;
    private Integer isIncome;
    private Double amount;
}
