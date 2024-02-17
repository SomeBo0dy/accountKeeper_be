package pers.xyj.modules.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xyj.modules.auth.domain.entity.Role;
import pers.xyj.modules.auth.mapper.RoleMapper;
import pers.xyj.modules.auth.service.RoleService;

/**
 * 角色表(Role)表服务实现类
 *
 * @author xyj
 * @since 2023-05-02 14:12:39
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

