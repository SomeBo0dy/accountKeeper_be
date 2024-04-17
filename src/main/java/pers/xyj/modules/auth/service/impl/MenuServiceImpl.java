package pers.xyj.modules.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.auth.domain.dto.MenuAddDto;
import pers.xyj.modules.auth.domain.entity.Menu;
import pers.xyj.modules.auth.mapper.MenuMapper;
import pers.xyj.modules.auth.service.MenuService;


@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public ResponseResult addMenu(MenuAddDto menuAddDto) {
        return ResponseResult.okResult("没做");
    }
}

