package pers.xyj.modules.accountKeeper.controller.accountKeeper;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.AddBillReminderDto;
import pers.xyj.modules.accountKeeper.domain.dto.AddBookDto;
import pers.xyj.modules.accountKeeper.domain.dto.EditBillReminderDto;
import pers.xyj.modules.accountKeeper.domain.entity.BillReminder;
import pers.xyj.modules.accountKeeper.domain.entity.Book;
import pers.xyj.modules.accountKeeper.service.BillReminderService;
import org.springframework.web.bind.annotation.*;
import pers.xyj.modules.common.annotation.SystemLog;


@Api(value = "BillReminderControllerApi", tags = {"账单提醒操作接口"})
@RestController
@RequestMapping("bills")
public class BillReminderController {

    @Autowired
    private BillReminderService billReminderService;
    @ApiOperation(value = "添加账单提醒")
    @SystemLog(businessName = "添加账单提醒")
    @PostMapping
    public ResponseResult addBillReminder(@RequestBody AddBillReminderDto reminderDto){
        return billReminderService.addBillReminder(reminderDto);
    }
    @ApiOperation(value = "删除账单提醒")
    @SystemLog(businessName = "删除账单提醒")
    @DeleteMapping("/{id}")
    public ResponseResult deleteBillReminder(@PathVariable("id") Integer id){
        return billReminderService.deleteBillReminder(id);
    }

    @ApiOperation(value = "编辑账单提醒")
    @SystemLog(businessName = "编辑账单提醒")
    @PutMapping
    public ResponseResult editBillReminder(@RequestBody EditBillReminderDto billReminder){
        return billReminderService.editBillReminder(billReminder);
    }
    @ApiOperation(value="获取账单提醒")
    @SystemLog(businessName = "获取账单提醒")
    @GetMapping
    public ResponseResult getBillReminders(Integer pageNum, Integer pageSize){
        return billReminderService.getBillReminders(pageNum,pageSize);
    }
}