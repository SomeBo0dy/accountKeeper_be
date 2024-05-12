package pers.xyj.modules.accountKeeper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.AddBillReminderDto;
import pers.xyj.modules.accountKeeper.domain.dto.EditBillReminderDto;
import pers.xyj.modules.accountKeeper.domain.entity.BillReminder;

import java.util.Date;


public interface BillReminderService extends IService<BillReminder> {

    ResponseResult getBillReminders(Date date, Integer pageNum, Integer pageSize);

    ResponseResult editBillReminder(EditBillReminderDto billReminder);

    ResponseResult deleteBillReminder(Integer id);

    ResponseResult addBillReminder(AddBillReminderDto reminderDto);
}

