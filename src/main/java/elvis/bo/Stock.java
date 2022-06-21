package elvis.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("stock")
public class Stock {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Date createTime;

    private Date dataTime;

    @JSONField(name = "f2")
    private Double nowPrice;// 最新价

    @JSONField(name = "f3")
    private Double fluctuation;// 涨跌幅

    @JSONField(name = "f4")
    private Double changeAmount;// 涨跌额

    @JSONField(name = "f5")
    private Long tradeAmount;// 成交量(手)

    @JSONField(name = "f6")
    private Double tradeVolume;// 成交额

    @JSONField(name = "f7")
    private Double amplitude;// 振幅

    @JSONField(name = "f8")
    private Double changeRate;// 换手率

    @JSONField(name = "f9")
    private Double perDynamic;// 市盈率(动态)

    @JSONField(name = "f10")
    private Double quantityRatio;// 量比

    @JSONField(name = "f12")
    private String code;// 股票代码

    @JSONField(name = "f13")
    private Integer stockType;// 股票类型

    @JSONField(name = "f15")
    private Double todayHigh;// 最高

    @JSONField(name = "f16")
    private Double todayLow;// 最低

    @JSONField(name = "f17")
    private Double todayStart;// 今开

    @JSONField(name = "f18")
    private Double yesterdayEnd;// 昨收

    @JSONField(name = "f23")
    private Double pbr;// 市净率
}
