package pers.xyj.modules.accountKeeper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.accountKeeper.domain.dto.AddTypeDto;
import pers.xyj.modules.accountKeeper.domain.entity.BookUser;
import pers.xyj.modules.accountKeeper.domain.vo.BookVo;
import pers.xyj.modules.accountKeeper.domain.vo.PageVo;
import pers.xyj.modules.accountKeeper.mapper.TypeMapper;
import pers.xyj.modules.accountKeeper.domain.entity.Type;
import pers.xyj.modules.accountKeeper.service.TypeService;
import org.springframework.stereotype.Service;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.common.utils.BeanCopyUtils;

import java.util.List;

/**
 * (Type)表服务实现类
 *
 * @author makejava
 * @since 2024-04-16 13:02:33
 */

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

    @Override
    public ResponseResult getTypesPage(Integer pageNum, Integer pageSize) {
        IPage<Type> page = new Page<>(pageNum, pageSize);
        page(page);
        PageVo pageVo = new PageVo(page.getRecords(), page.getPages(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult deleteType(Integer id) {
        int delete = typeMapper.deleteById(id);
        if (delete != 1){
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult addType(AddTypeDto typeDto) {
        Type type = BeanCopyUtils.copeBean(typeDto, Type.class);
        int insert = typeMapper.insert(type);
        if (insert != 1){
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult editType(Type type) {
        int update = typeMapper.updateById(type);
        if (update != 1){
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }
}

