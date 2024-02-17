package pers.xyj.modules.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.modules.auth.domain.dto.MenuAddDto;
import pers.xyj.modules.auth.domain.entity.Menu;
import pers.xyj.modules.market.domain.ResponseResult;

/**
 * 菜单表(Menu)表服务接口
 */
public interface MenuService extends IService<Menu> {

    ResponseResult addMenu(MenuAddDto menuAddDto);
}
