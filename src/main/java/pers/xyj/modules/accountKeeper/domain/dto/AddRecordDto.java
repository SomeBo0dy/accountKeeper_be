package pers.xyj.modules.accountKeeper.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRecordDto {
    private Double amount;

    private Integer tId;

    private Integer bId;

    private String description;
//    @ApiModelProperty(value = "日期yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Long timeInMillis;
}
