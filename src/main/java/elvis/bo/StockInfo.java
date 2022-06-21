package elvis.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("stock_info")
@Data
public class StockInfo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String code;

    private String name;

    private Date createTime;

    private Integer stockType;

    private Integer histSaved;

    private Date updateDate;
}
