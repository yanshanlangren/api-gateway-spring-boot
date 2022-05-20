package elvis.dao.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import elvis.bo.User;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@TableName("user")
public interface UserMapper extends BaseMapper<User> {
    Integer insertBatchSomeColumn(Collection<User> entityList);
}
