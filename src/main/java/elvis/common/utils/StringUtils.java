package elvis.common.utils;

import java.util.Date;

public class StringUtils {
    public static String reveal(String input) {
        String[] inputArr = input.split("\\(");
        Date dataTime = new Date(Long.parseLong(inputArr[0].split("_")[1]));
        String jsonInput = inputArr[1].substring(0, inputArr[1].length() - 1);
        //清除无效的 "-"数据
        return jsonInput.replace("\"-\"", "0");
    }
}
