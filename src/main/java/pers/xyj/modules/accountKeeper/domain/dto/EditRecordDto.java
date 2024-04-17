package pers.xyj.modules.accountKeeper.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditRecordDto {
    private Integer id;

    private Double amount;

    private String type;

    private Integer bId;

    private String description;

    private Date createTime;
}
