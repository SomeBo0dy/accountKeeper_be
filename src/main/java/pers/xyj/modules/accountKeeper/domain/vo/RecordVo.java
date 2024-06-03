package pers.xyj.modules.accountKeeper.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordVo {
    private Integer id;

    private Double amount;

    private Integer tId;

    private Integer isIncome;

    private String typeName;

    private String imgUrl;

    private String description;

    private Long createBy;
    private String nickName;


}
