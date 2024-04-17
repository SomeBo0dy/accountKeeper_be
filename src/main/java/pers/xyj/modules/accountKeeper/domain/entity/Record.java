package pers.xyj.modules.accountKeeper.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ak_record")
public class Record {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Double amount;

    private Integer tId;

    private Integer bId;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
