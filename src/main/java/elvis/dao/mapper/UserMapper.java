package elvis.dao.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import elvis.bo.User;
import org.springframework.stereotype.Repository;

@Repository
@TableName("user")
public interface UserMapper extends BaseMapper<User> {
}
