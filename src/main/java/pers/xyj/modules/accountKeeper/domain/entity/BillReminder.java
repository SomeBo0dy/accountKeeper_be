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
@TableName("ak_bill_reminder")
public class BillReminder {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String billName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reminderTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
}