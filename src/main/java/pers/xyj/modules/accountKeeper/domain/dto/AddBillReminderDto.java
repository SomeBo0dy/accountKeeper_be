package pers.xyj.modules.accountKeeper.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBillReminderDto {

    private String billName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reminderTime;
}
