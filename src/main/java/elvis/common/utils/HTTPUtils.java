package elvis.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HTTPUtils {
    public static String getPage(String urlParam, String requestType) {
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
