package pers.xyj.modules.accountKeeper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.mapper.TypeMapper;
import pers.xyj.modules.accountKeeper.domain.entity.Type;
import pers.xyj.modules.accountKeeper.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Type)表服务实现类
 *
 * @author makejava
 * @since 2024-04-16 13:02:33
 */
@Data
@Slf4j
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public ResponseResult getTypes() {
        List<Type> types = typeMapper.selectList(null);
        return ResponseResult.okResult(types);
    }
}

