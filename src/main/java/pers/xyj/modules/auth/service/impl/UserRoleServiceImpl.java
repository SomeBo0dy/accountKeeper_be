package pers.xyj.modules.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xyj.modules.auth.domain.entity.UserRole;
import pers.xyj.modules.auth.mapper.UserRoleMapper;
import pers.xyj.modules.auth.service.UserRoleService;


/**
 * (UserRole)表服务实现类
 *
 * @author xyj
 * @since 2023-05-02 14:17:28
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}

