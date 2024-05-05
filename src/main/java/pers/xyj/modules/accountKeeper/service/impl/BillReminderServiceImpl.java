package pers.xyj.modules.accountKeeper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.AddBillReminderDto;
import pers.xyj.modules.accountKeeper.domain.dto.EditBillReminderDto;
import pers.xyj.modules.accountKeeper.domain.vo.BillReminderVo;
import pers.xyj.modules.accountKeeper.domain.vo.PageVo;
import pers.xyj.modules.accountKeeper.mapper.BillReminderMapper;
import pers.xyj.modules.accountKeeper.domain.entity.BillReminder;
import pers.xyj.modules.accountKeeper.service.BillReminderService;
import org.springframework.stereotype.Service;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.common.utils.BeanCopyUtils;
import pers.xyj.modules.common.utils.SecurityUtils;

import java.util.List;

@Slf4j
@Service
public class BillReminderServiceImpl extends ServiceImpl<BillReminderMapper, BillReminder> implements BillReminderService {

    @Autowired
    private BillReminderMapper billReminderMapper;
    @Override
    public ResponseResult getBillReminders(Integer pageNum, Integer pageSize) {
        Long userId = SecurityUtils.getUserId();
        Page<BillReminder> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<BillReminder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BillReminder::getUId, userId);
        page(page, queryWrapper);
        List<BillReminderVo> billReminderVos = BeanCopyUtils.copyBeanList(page.getRecords(), BillReminderVo.class);
        PageVo pageVo = new PageVo(billReminderVos, page.getPages(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult editBillReminder(EditBillReminderDto billReminder) {
        LambdaUpdateWrapper<BillReminder> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(BillReminder::getId,billReminder.getId());
        updateWrapper.set(BillReminder::getBillName,billReminder.getBillName());
        updateWrapper.set(BillReminder::getReminderTime,billReminder.getReminderTime());
        int update = billReminderMapper.update(null, updateWrapper);
        if (update != 1){
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteBillReminder(Integer id) {
        int delete = billReminderMapper.deleteById(id);
        if (delete != 1){
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult addBillReminder(AddBillReminderDto reminderDto) {
        BillReminder billReminder = BeanCopyUtils.copeBean(reminderDto, BillReminder.class);
        billReminder.setUId(SecurityUtils.getUserId());
        int insert = billReminderMapper.insert(billReminder);
        if (insert != 1){
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }
}

