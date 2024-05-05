package pers.xyj.modules.accountKeeper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.entity.ShareCode;

public interface ShareCodeService extends IService<ShareCode> {

    ResponseResult getShareCode(Integer bookId);

    ResponseResult joinBook(String code);

    ResponseResult quitBook(Integer bookId);
}


