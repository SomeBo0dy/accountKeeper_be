package pers.xyj.modules.accountKeeper.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookVo {
    private Integer id;

    private String name;
    //描绘
    private String description;

    private String priority;

    private Long createBy;

    private Date createTime;

    private Integer memberCount;

    private Double amount;
}
