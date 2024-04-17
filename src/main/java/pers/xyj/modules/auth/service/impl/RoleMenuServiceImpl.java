package pers.xyj.modules.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xyj.modules.auth.domain.entity.RoleMenu;
import pers.xyj.modules.auth.mapper.RoleMenuMapper;
import pers.xyj.modules.auth.service.RoleMenuService;


@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}

