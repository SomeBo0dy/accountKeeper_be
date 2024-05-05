package pers.xyj.modules.accountKeeper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.entity.BookUser;
import pers.xyj.modules.accountKeeper.mapper.BookUserMapper;
import pers.xyj.modules.accountKeeper.mapper.ShareCodeMapper;
import pers.xyj.modules.accountKeeper.domain.entity.ShareCode;
import pers.xyj.modules.accountKeeper.service.ShareCodeService;
import org.springframework.stereotype.Service;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.common.utils.SecurityUtils;
import pers.xyj.modules.common.utils.ShareCodeUtils;

import java.util.Objects;

@Slf4j
@Service
public class ShareCodeServiceImpl extends ServiceImpl<ShareCodeMapper, ShareCode> implements ShareCodeService {

    @Autowired
    private ShareCodeMapper shareCodeMapper;

    @Autowired
    private BookUserMapper bookUserMapper;

    @Override
    public ResponseResult getShareCode(Integer bookId) {
        LambdaQueryWrapper<ShareCode> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShareCode::getBId, bookId);
        ShareCode shareCode = shareCodeMapper.selectOne(wrapper);
        if (Objects.isNull(shareCode)){
            String code = ShareCodeUtils.idToCode(bookId);
            ShareCode newShareCode = new ShareCode();
            newShareCode.setBId(bookId);
            newShareCode.setShareCode(code);
            shareCodeMapper.insert(newShareCode);
            shareCode = newShareCode;
        }
        return ResponseResult.okResult(shareCode);
    }

    @Override
    @Transactional
    public ResponseResult joinBook(String code) {
        LambdaQueryWrapper<ShareCode> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShareCode::getShareCode, code);
        ShareCode shareCode = shareCodeMapper.selectOne(wrapper);
        if (Objects.isNull(shareCode)){
            return ResponseResult.okResult("邀请码不存在");
        }
        Integer bId = shareCode.getBId();
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<BookUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BookUser::getUId,userId);
        queryWrapper.eq(BookUser::getBId,bId);
        BookUser theBookUser = bookUserMapper.selectOne(queryWrapper);
        if (Objects.nonNull(theBookUser)){
            return ResponseResult.okResult("无需重复加入");
        }

        LambdaQueryWrapper<BookUser> countQueryWrapper = new LambdaQueryWrapper<>();
        countQueryWrapper.eq(BookUser::getUId,userId);
        Integer count = bookUserMapper.selectCount(countQueryWrapper);
        BookUser bookUser = new BookUser(userId, bId, count + 1);
        bookUserMapper.insert(bookUser);
        Integer sharedCount = shareCode.getSharedCount();
        shareCode.setSharedCount(sharedCount + 1);
        shareCodeMapper.updateById(shareCode);
        return ResponseResult.okResult("加入成功");
    }

    @Override
    public ResponseResult quitBook(Integer bookId) {
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<BookUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BookUser::getBId, bookId);
        queryWrapper.eq(BookUser::getUId, userId);
        int delete = bookUserMapper.delete(queryWrapper);
        if (delete != 1){
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }
}

