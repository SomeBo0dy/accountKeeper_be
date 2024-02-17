package pers.xyj.modules.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xyj.modules.auth.domain.dto.MenuAddDto;
import pers.xyj.modules.auth.domain.entity.Menu;
import pers.xyj.modules.auth.mapper.MenuMapper;
import pers.xyj.modules.auth.service.MenuService;
import pers.xyj.modules.market.domain.ResponseResult;

/**
 * 菜单表(Menu)表服务实现类
 *
 * @author xyj
 * @since 2023-05-02 14:10:52
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public ResponseResult addMenu(MenuAddDto menuAddDto) {
        return ResponseResult.okResult("没做");
    }
}

