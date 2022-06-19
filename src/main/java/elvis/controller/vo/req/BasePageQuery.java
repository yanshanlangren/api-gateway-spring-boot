package elvis.controller.vo.req;

import lombok.Data;

@Data
public class BasePageQuery {
    private int limit;
    private int offset;
    private String sortBy;
    private int asc;
}
