package pers.xyj.modules.accountKeeper.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBillReminderDto {

    private String billName;

    private Date reminderTime;
}
