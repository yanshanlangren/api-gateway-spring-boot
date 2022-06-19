package elvis.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import elvis.bo.Stock;
import elvis.common.utils.HTTPUtils;
import elvis.common.utils.PageUtils;
import elvis.common.utils.StringUtils;
import elvis.controller.vo.req.StockQuery;
import elvis.dao.mapper.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class StockService {

    @Autowired
    StockMapper stockMapper;

    /**
     * 按页面查询个股信息
     *
     * @param start
     * @param end
     * @return
     */
    public int scrapyDailyStockInfoByPageNumber(int start, int end) {
        List<Stock> list = new ArrayList<>();
        int size = 0;
        for (int i = start; i <= end; i++) {
            String url = "http://60.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112408744624686429123_1578798932591&pn=" + i + "&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:0+t:6,m:0+t:13,m:0+t:80,m:1+t:2,m:1+t:23&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f128,f136,f115,f152&_=1586266306109";
            String responseString = HTTPUtils.getPage(url, "POST");
            List<Stock> respList = getStockData(responseString);
            size += respList.size();
            log.info("正在解析第" + size + "条数据");
            list.addAll(respList);
        }
        Date now = new Date();
        list.forEach(x -> {
            x.setCreateTime(now);
            x.setDataTime(now);
        });
        long t0 = System.currentTimeMillis();
        stockMapper.insertBatchSomeColumn(list);
        log.info("插入" + size + "条数据总用时" + (System.currentTimeMillis() - t0) + "ms");
        return size;
    }

    /**
     * 查询个股详细
     */
    public void getDayInfoByStock(Integer stockType, String stockCode) {
        String secId = String.format("%d.%s", stockType, stockCode);
        String url = "http://push2.eastmoney.com/api/qt/stock/trends2/get?fields1=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f17&fields2=f51,f52,f53,f54,f55,f56,f57,f58&ut=fa5fd1943c7b386f172d6893dbfba10b&ndays=1&iscr=0&secid=" + secId + "&cb=jQuery112405680347133483739_1655639429810&_=1655639429839";
        String resp = HTTPUtils.getPage(url, "GET");
        String jsonInput = StringUtils.reveal(resp);
        System.out.println(resp);
        JSONObject root = JSONObject.parseObject(jsonInput);
        JSONObject data = (JSONObject) root.get("data");
        JSONArray klines = data.getJSONArray("klines");
    }

    public int scrapyDailyStockInfo() {
        List<Stock> list = new ArrayList<>();
        int size = 0;
        int i = 1;
        int lastSize = 0;
        while (i == 1 || lastSize > 0) {
            String url = "http://60.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112408744624686429123_1578798932591&pn=" + i++ + "&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:0+t:6,m:0+t:13,m:0+t:80,m:1+t:2,m:1+t:23&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f128,f136,f115,f152&_=1586266306109";
            String responseString = HTTPUtils.getPage(url, "POST");
            List<Stock> respList = getStockData(responseString);
            lastSize = respList.size();
            size += respList.size();
            log.info("正在解析第" + size + "条数据");
            list.addAll(respList);
        }
        Date now = new Date();
        list.forEach(x -> x.setCreateTime(now));
        long t0 = System.currentTimeMillis();
        stockMapper.insertBatchSomeColumn(list);
        log.info("插入" + size + "条数据总用时" + (System.currentTimeMillis() - t0) + "ms");
        return size;
    }

    public List<Stock> getStockData(String input) {
        List<Stock> list = new ArrayList<>();
//        String[] inputArr = input.split("\\(");
//        Date dataTime = new Date(Long.parseLong(inputArr[0].split("_")[1]));
//        String jsonInput = inputArr[1].substring(0, inputArr[1].length() - 1);
//        //清除无效的 "-"数据
//        jsonInput = jsonInput.replace("\"-\"", "0");
        String jsonInput = StringUtils.reveal(input);
        JSONObject root = (JSONObject) JSONObject.parse(jsonInput);
        JSONObject data = (JSONObject) root.get("data");
        if (data == null) {
            return list;
        }
        JSONArray dataArr = (JSONArray) data.get("diff");
        for (Object jsonObject : dataArr) {
            Stock s = JSONObject.toJavaObject((JSON) jsonObject, Stock.class);
//            s.setDataTime(dataTime);
            list.add(s);
        }
        return list;
    }


    public Page<Stock> listPage(StockQuery query) {
        Page<Stock> page = PageUtils.instanceOf(query);

        LambdaQueryWrapper<Stock> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(query.getCode() != null, Stock::getCode, query.getCode());
        return stockMapper.selectPage(page, wrapper);
    }
}
