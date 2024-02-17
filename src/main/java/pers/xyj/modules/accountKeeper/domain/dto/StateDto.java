package pers.xyj.modules.accountKeeper.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StateDto {
    @ApiModelProperty(value = "id")
    Long Id;
    @ApiModelProperty(value = "改变到的状态（0正常 1停用）")
    String toState;
}
