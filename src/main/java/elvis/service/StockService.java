package elvis.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StockService {
    static final String regex = "\"f2\":(?P<end>.+?),.*?\"f3\":(?P<fluctuation>.+?),.*?\"f4\":(?P<change_amount>.+?),.*?\"f5\":(?P<trading_amount>.+?),.*?\"f6\":(?P<trading_volume>.+?),.*?\"f7\":(?P<zhenfu>.+?),.*?\"f8\":(?P<huanshoulv>.+?),.*?\"f9\":(?P<shiyinglv_dongtai>.+?),.*?\"f10\":(?P<liangbi>.+?),.*?\"f11\":(?P<liangbi>.+?),.*?\"f12\":\"(?P<number>.+?)\",.*?\"f13\":(?P<gupiaoleixing>.+?),.*?\"f14\":\"(?P<name>.+?)\",.*?\"f15\":(?P<max>.+?),.*?\"f16\":(?P<min>.+?),.*?\"f17\":(?P<start>.+?),.*?\"f18\":(?P<zuoshou>.+?),.*?\"f23\":(?P<shijinglv>.+?),";

    public void scrapyDailyStockInfo(int start, int end) {
        for (int i = start; i <= end; i++) {
            String url = "http://60.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112408744624686429123_1578798932591&pn=" + i + "&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:0+t:6,m:0+t:13,m:0+t:80,m:1+t:2,m:1+t:23&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f128,f136,f115,f152&_=1586266306109";
            String responseString = getPage(url, "POST");
        }
    }

    public String getStockData(String input) {
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        MatchResult result = matcher.toMatchResult();
        System.out.println("First Capturing Group - Match String end(): " + result.end());
        return "";
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

}
