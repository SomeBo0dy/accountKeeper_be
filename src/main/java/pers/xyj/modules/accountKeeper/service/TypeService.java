package pers.xyj.modules.accountKeeper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.AddTypeDto;
import pers.xyj.modules.accountKeeper.domain.entity.Type;

public interface TypeService extends IService<Type> {

    ResponseResult getTypes();

    ResponseResult getTypesPage(Integer pageNum, Integer pageSize);

    ResponseResult deleteType(Integer id);

    ResponseResult addType(AddTypeDto typeDto);

    ResponseResult editType(Type type);
}

