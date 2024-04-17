package pers.xyj.modules.accountKeeper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.entity.Type;

/**
 * (Type)表服务接口
 *
 * @author makejava
 * @since 2024-04-16 13:02:33
 */
public interface TypeService extends IService<Type> {

    ResponseResult getTypes();
}

