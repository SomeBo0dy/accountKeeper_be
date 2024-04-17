package pers.xyj.modules.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.modules.accountKeeper.domain.ResponseResult;
import pers.xyj.modules.auth.domain.dto.MenuAddDto;
import pers.xyj.modules.auth.domain.entity.Menu;

/**
 * 菜单表(Menu)表服务接口
 */
public interface MenuService extends IService<Menu> {

    ResponseResult addMenu(MenuAddDto menuAddDto);
}
