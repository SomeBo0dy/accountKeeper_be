package pers.xyj.modules.accountKeeper.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookStatisticsVo {
    private Integer incomeCount;
    private Integer outcomeCount;
    private Double incomeAmountSum;
    private Double outcomeAmountSum;
    private ArrayList<TypeNameAndCountVo> incomeStatistics;
    private ArrayList<TypeNameAndCountVo> outcomeStatistics;
}
