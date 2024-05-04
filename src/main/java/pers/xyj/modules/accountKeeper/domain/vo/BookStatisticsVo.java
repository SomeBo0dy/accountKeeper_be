package pers.xyj.modules.accountKeeper.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookStatisticsVo {
    private Integer recordCountTotal;
    private Double recordAmountTotal;
    private ArrayList<TypeNameAndCountVo> typeList;
}
