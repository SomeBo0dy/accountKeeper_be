package pers.xyj.modules.accountKeeper.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

    private Long uId;

    private String billName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reminderTime;

}