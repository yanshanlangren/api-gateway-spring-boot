package elvis.dao.mapper;

import elvis.bo.Domain;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DomainMapper {
    @Select("select * from domain")
    @Results({@Result(property = "domain_id", column = "domain_id"),
            @Result(property = "domain_name", column = "domain_name"),
            @Result(property = "description", column = "description")})
    List<Domain> getAll();
}
