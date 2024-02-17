package pers.xyj.modules.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xyj.modules.auth.domain.entity.RoleMenu;
import pers.xyj.modules.auth.mapper.RoleMenuMapper;
import pers.xyj.modules.auth.service.RoleMenuService;

/**
 * (RoleMenu)表服务实现类
 *
 * @author xyj
 * @since 2023-05-02 14:14:26
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}

