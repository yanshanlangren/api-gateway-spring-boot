package elvis.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import elvis.bo.Stock;
import elvis.common.utils.PageUtils;
import elvis.controller.vo.req.StockQuery;
import elvis.dao.mapper.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class StockService {

    @Autowired
    StockMapper stockMapper;

    public int scrapyDailyStockInfoByPageNumber(int start, int end) {
        List<Stock> list = new ArrayList<>();
        int size = 0;
        for (int i = start; i <= end; i++) {
            String url = "http://60.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112408744624686429123_1578798932591&pn=" + i + "&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:0+t:6,m:0+t:13,m:0+t:80,m:1+t:2,m:1+t:23&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f128,f136,f115,f152&_=1586266306109";
            String responseString = getPage(url, "POST");
            List<Stock> respList = getStockData(responseString);
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

    public int scrapyDailyStockInfo() {
        List<Stock> list = new ArrayList<>();
        int size = 0;
        int i = 1;
        int lastSize = 0;
        while (i == 1 || lastSize > 0) {
            String url = "http://60.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112408744624686429123_1578798932591&pn=" + i++ + "&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:0+t:6,m:0+t:13,m:0+t:80,m:1+t:2,m:1+t:23&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f128,f136,f115,f152&_=1586266306109";
            String responseString = getPage(url, "POST");
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
        String[] inputArr = input.split("\\(");
        Date dataTime = new Date(Long.parseLong(inputArr[0].split("_")[1]));
        String jsonInput = inputArr[1].substring(0, inputArr[1].length() - 1);
        //清除无效的 "-"数据
        jsonInput = jsonInput.replace("\"-\"", "0");
        JSONObject root = (JSONObject) JSONObject.parse(jsonInput);
        JSONObject data = (JSONObject) root.get("data");
        if (data == null) {
            return list;
        }
        JSONArray dataArr = (JSONArray) data.get("diff");
        for (Object jsonObject : dataArr) {
            Stock s = JSONObject.toJavaObject((JSON) jsonObject, Stock.class);
            s.setDataTime(dataTime);
            list.add(s);
        }
        return list;
    }

    public String getPage(String urlParam, String requestType) {
        HttpURLConnection connection = null;

        BufferedReader buffer = null;
        StringBuffer resultBuffer = null;

        try {
            URL url = new URL(urlParam);
            connection = (HttpURLConnection) url.openConnection();

            //请求头
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 " + "(KHTML, like Gecko) Chrome/75.0.3770.90 Safari/537.36");

            connection.setRequestMethod(requestType);
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                resultBuffer = new StringBuffer();
                String line;
                buffer = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                while ((line = buffer.readLine()) != null) {
                    resultBuffer.append(line);
                }
                return resultBuffer.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public Page<Stock> listPage(StockQuery query) {
        Page<Stock> page = PageUtils.instanceOf(query);

        LambdaQueryWrapper<Stock> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(query.getCode() != null, Stock::getCode, query.getCode());
        return stockMapper.selectPage(page, wrapper);
    }
}
