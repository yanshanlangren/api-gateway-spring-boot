package elvis.controller.vo.req;

import lombok.Data;

@Data
public class StockQuery extends BasePageQuery {
    private String code;
}
