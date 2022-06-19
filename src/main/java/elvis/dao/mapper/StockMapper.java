package elvis.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import elvis.bo.Stock;

import java.util.Collection;

public interface StockMapper extends BaseMapper<Stock> {
    /**
     * 批量导入
     * @param entityList
     * @return
     */
    Integer insertBatchSomeColumn(Collection<Stock> entityList);
}
