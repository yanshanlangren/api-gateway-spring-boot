package elvis.common.utils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import elvis.controller.vo.req.BasePageQuery;

import java.util.ArrayList;
import java.util.Collections;

public class PageUtils {
    public static <T> Page<T> instanceOf(BasePageQuery query) {
        Page<T> page = new Page<>(query.getOffset(), query.getLimit());
        page.setOrders(new ArrayList<>(Collections.singletonList(new OrderItem(query.getSortBy() == null ? "create_time" : query.getSortBy(), query.getAsc() == 1))));
        return page;
    }
}
