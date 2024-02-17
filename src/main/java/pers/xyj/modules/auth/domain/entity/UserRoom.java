package pers.xyj.modules.auth.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户机房管理员(UserRoom)表实体类
 *
 * @author xyj
 * @since 2023-05-03 17:21:15
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("mg_user_room")
public class UserRoom extends Model<UserRoom> {

    private Long userId;

    private Long roomId;

}