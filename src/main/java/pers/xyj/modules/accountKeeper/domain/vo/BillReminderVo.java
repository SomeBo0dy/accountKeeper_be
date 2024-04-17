package pers.xyj.modules.accountKeeper.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillReminderVo {
    private Integer id;

    private String billName;

    private Date reminderTime;
}
