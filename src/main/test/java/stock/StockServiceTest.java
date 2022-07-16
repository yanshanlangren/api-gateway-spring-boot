package stock;

import elvis.common.utils.HTTPUtils;
import elvis.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = elvis.App.class)
public class StockServiceTest {

    @Autowired
    StockService stockService;

    @Test
    public void testHttpRequest() {
        System.out.println(HTTPUtils.getPage("http://60.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112408744624686429123_1578798932591&pn=1&pz=20&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:0+t:6,m:0+t:13,m:0+t:80,m:1+t:2,m:1+t:23&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f128,f136,f115,f152&_=1586266306109", "POST"));
    }

    @Test
    public void testRegex() {
        System.out.println(stockService.getStockData("jQuery112408744624686429123_1578798932591({\"rc\":0,\"rt\":6,\"svr\":182993875,\"lt\":1,\"full\":1,\"dlmkts\":\"\",\"data\":{\"total\":4905,\"diff\":[{\"f1\":2,\"f2\":7.24,\"f3\":20.07,\"f4\":1.21,\"f5\":574947,\"f6\":396096176.0,\"f7\":18.57,\"f8\":26.71,\"f9\":37.19,\"f10\":3.97,\"f11\":0.0,\"f12\":\"300192\",\"f13\":0,\"f14\":\"科德教育\",\"f15\":7.24,\"f16\":6.12,\"f17\":6.14,\"f18\":6.03,\"f20\":2382997702,\"f21\":1558535404,\"f22\":0.0,\"f23\":3.5,\"f24\":10.53,\"f25\":-12.88,\"f62\":34731941.0,\"f115\":-5.14,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":46.54,\"f3\":20.01,\"f4\":7.76,\"f5\":79499,\"f6\":348261360.0,\"f7\":19.44,\"f8\":5.27,\"f9\":33.03,\"f10\":2.75,\"f11\":0.0,\"f12\":\"300260\",\"f13\":0,\"f14\":\"新莱应材\",\"f15\":46.54,\"f16\":39.0,\"f17\":39.13,\"f18\":38.78,\"f20\":10544070148,\"f21\":7023189255,\"f22\":0.0,\"f23\":7.94,\"f24\":-13.01,\"f25\":-1.34,\"f62\":-5405569.0,\"f115\":48.46,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":20.7,\"f3\":20.0,\"f4\":3.45,\"f5\":78239,\"f6\":154721419.0,\"f7\":19.54,\"f8\":7.82,\"f9\":61.33,\"f10\":5.18,\"f11\":0.0,\"f12\":\"688189\",\"f13\":1,\"f14\":\"南新制药\",\"f15\":20.7,\"f16\":17.33,\"f17\":17.45,\"f18\":17.25,\"f20\":2898000000,\"f21\":2070000000,\"f22\":0.0,\"f23\":2.06,\"f24\":-1.38,\"f25\":-41.23,\"f62\":25487219.0,\"f115\":-16.12,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":21.54,\"f3\":20.0,\"f4\":3.59,\"f5\":635227,\"f6\":1263151344.0,\"f7\":21.28,\"f8\":24.32,\"f9\":324.83,\"f10\":3.9,\"f11\":0.0,\"f12\":\"300052\",\"f13\":0,\"f14\":\"中青宝\",\"f15\":21.54,\"f16\":17.72,\"f17\":17.78,\"f18\":17.95,\"f20\":5643775313,\"f21\":5626533470,\"f22\":0.0,\"f23\":8.71,\"f24\":-16.8,\"f25\":-34.05,\"f62\":127279193.0,\"f115\":-114.29,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":76.39,\"f3\":20.0,\"f4\":12.73,\"f5\":18328,\"f6\":131164032.0,\"f7\":19.76,\"f8\":9.16,\"f9\":191.3,\"f10\":5.09,\"f11\":0.0,\"f12\":\"301051\",\"f13\":0,\"f14\":\"信濠光电\",\"f15\":76.39,\"f16\":63.81,\"f17\":65.0,\"f18\":63.66,\"f20\":6111200000,\"f21\":1527800000,\"f22\":0.0,\"f23\":2.22,\"f24\":-9.06,\"f25\":-35.89,\"f62\":12445453.0,\"f115\":70.69,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":6.26,\"f3\":19.92,\"f4\":1.04,\"f5\":745150,\"f6\":446102000.0,\"f7\":18.77,\"f8\":11.77,\"f9\":400.57,\"f10\":3.02,\"f11\":0.0,\"f12\":\"300359\",\"f13\":0,\"f14\":\"全通教育\",\"f15\":6.26,\"f16\":5.28,\"f17\":5.3,\"f18\":5.22,\"f20\":3966733022,\"f21\":3964197722,\"f22\":0.0,\"f23\":5.92,\"f24\":20.62,\"f25\":4.68,\"f62\":87782142.0,\"f115\":1968.16,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":5.12,\"f3\":19.91,\"f4\":0.85,\"f5\":1587283,\"f6\":776679712.0,\"f7\":15.93,\"f8\":21.4,\"f9\":-25.96,\"f10\":1.13,\"f11\":0.0,\"f12\":\"300010\",\"f13\":0,\"f14\":\"豆神教育\",\"f15\":5.12,\"f16\":4.44,\"f17\":4.6,\"f18\":4.27,\"f20\":4445822193,\"f21\":3797461888,\"f22\":0.0,\"f23\":81.13,\"f24\":47.55,\"f25\":22.2,\"f62\":107664573.0,\"f115\":-7.53,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":3.08,\"f3\":16.23,\"f4\":0.43,\"f5\":595369,\"f6\":174741547.0,\"f7\":20.75,\"f8\":20.1,\"f9\":-5.23,\"f10\":2.95,\"f11\":1.32,\"f12\":\"300338\",\"f13\":0,\"f14\":\"ST开元\",\"f15\":3.18,\"f16\":2.63,\"f17\":2.63,\"f18\":2.65,\"f20\":1046028651,\"f21\":912521274,\"f22\":0.33,\"f23\":-70.62,\"f24\":-47.26,\"f25\":-54.3,\"f62\":11728368.0,\"f115\":-2.46,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":29.02,\"f3\":14.79,\"f4\":3.74,\"f5\":126210,\"f6\":352896416.0,\"f7\":18.55,\"f8\":12.04,\"f9\":5.04,\"f10\":2.92,\"f11\":0.1,\"f12\":\"300533\",\"f13\":0,\"f14\":\"冰川网络\",\"f15\":29.98,\"f16\":25.29,\"f17\":25.4,\"f18\":25.28,\"f20\":4377054678,\"f21\":3041390228,\"f22\":-0.17,\"f23\":2.34,\"f24\":72.74,\"f25\":45.17,\"f62\":18611196.0,\"f115\":26.0,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":11.16,\"f3\":14.58,\"f4\":1.42,\"f5\":439865,\"f6\":469623040.0,\"f7\":15.81,\"f8\":10.09,\"f9\":19.73,\"f10\":3.61,\"f11\":0.18,\"f12\":\"300360\",\"f13\":0,\"f14\":\"炬华科技\",\"f15\":11.3,\"f16\":9.76,\"f17\":9.76,\"f18\":9.74,\"f20\":5628960326,\"f21\":4865119773,\"f22\":0.09,\"f23\":2.14,\"f24\":19.87,\"f25\":-8.45,\"f62\":20710195.0,\"f115\":17.16,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":49.0,\"f3\":13.98,\"f4\":6.01,\"f5\":24265,\"f6\":116672410.0,\"f7\":17.1,\"f8\":12.83,\"f9\":-408.06,\"f10\":3.9,\"f11\":-0.06,\"f12\":\"688670\",\"f13\":1,\"f14\":\"金迪克\",\"f15\":50.8,\"f16\":43.45,\"f17\":43.45,\"f18\":42.99,\"f20\":4312000000,\"f21\":927080000,\"f22\":-0.1,\"f23\":3.05,\"f24\":-2.68,\"f25\":-34.82,\"f62\":3217556.0,\"f115\":50.66,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":15.43,\"f3\":13.54,\"f4\":1.84,\"f5\":135716,\"f6\":200929340.0,\"f7\":16.63,\"f8\":8.48,\"f9\":320.49,\"f10\":4.01,\"f11\":0.46,\"f12\":\"300585\",\"f13\":0,\"f14\":\"奥联电子\",\"f15\":16.06,\"f16\":13.8,\"f17\":14.1,\"f18\":13.59,\"f20\":2640244443,\"f21\":2468800000,\"f22\":0.13,\"f23\":3.77,\"f24\":15.24,\"f25\":9.74,\"f62\":23990925.0,\"f115\":116.3,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":21.89,\"f3\":12.03,\"f4\":2.35,\"f5\":17912,\"f6\":37876331.0,\"f7\":15.1,\"f8\":2.74,\"f9\":-66.77,\"f10\":3.72,\"f11\":-0.23,\"f12\":\"688607\",\"f13\":1,\"f14\":\"康众医疗\",\"f15\":22.39,\"f16\":19.44,\"f17\":19.74,\"f18\":19.54,\"f20\":1929144401,\"f21\":1431768314,\"f22\":0.05,\"f23\":2.3,\"f24\":-16.42,\"f25\":-42.38,\"f62\":543627.0,\"f115\":34.81,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":26.11,\"f3\":11.82,\"f4\":2.76,\"f5\":40254,\"f6\":102900319.0,\"f7\":12.76,\"f8\":4.19,\"f9\":78.55,\"f10\":5.16,\"f11\":-0.15,\"f12\":\"688010\",\"f13\":1,\"f14\":\"福光股份\",\"f15\":26.44,\"f16\":23.46,\"f17\":23.46,\"f18\":23.35,\"f20\":4010024532,\"f21\":2506239970,\"f22\":0.0,\"f23\":2.25,\"f24\":12.2,\"f25\":-7.25,\"f62\":7130522.0,\"f115\":86.28,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":16.48,\"f3\":11.8,\"f4\":1.74,\"f5\":65025,\"f6\":102177731.0,\"f7\":11.06,\"f8\":14.47,\"f9\":30.29,\"f10\":1.85,\"f11\":1.23,\"f12\":\"688722\",\"f13\":1,\"f14\":\"同益中\",\"f15\":16.48,\"f16\":14.85,\"f17\":14.9,\"f18\":14.74,\"f20\":3702507216,\"f21\":740501773,\"f22\":1.17,\"f23\":3.59,\"f24\":36.09,\"f25\":20.38,\"f62\":7977188.0,\"f115\":52.64,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":48.4,\"f3\":11.26,\"f4\":4.9,\"f5\":93002,\"f6\":445320352.0,\"f7\":12.34,\"f8\":27.51,\"f9\":-5694.8,\"f10\":4.17,\"f11\":0.29,\"f12\":\"301207\",\"f13\":0,\"f14\":\"华兰疫苗\",\"f15\":49.39,\"f16\":44.02,\"f17\":44.5,\"f18\":43.5,\"f20\":19360484000,\"f21\":1636226856,\"f22\":0.02,\"f23\":3.89,\"f24\":-9.55,\"f25\":-14.61,\"f62\":32821819.0,\"f115\":31.01,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":116.5,\"f3\":10.96,\"f4\":11.51,\"f5\":26964,\"f6\":305421664.0,\"f7\":9.3,\"f8\":9.43,\"f9\":41.95,\"f10\":3.62,\"f11\":0.42,\"f12\":\"688383\",\"f13\":1,\"f14\":\"新益昌\",\"f15\":117.76,\"f16\":108.0,\"f17\":109.0,\"f18\":104.99,\"f20\":11898564400,\"f21\":3330215993,\"f22\":0.02,\"f23\":9.01,\"f24\":20.94,\"f25\":-3.51,\"f62\":16360703.0,\"f115\":45.01,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":11.4,\"f3\":10.68,\"f4\":1.1,\"f5\":979887,\"f6\":1085499312.0,\"f7\":16.5,\"f8\":15.07,\"f9\":-129.76,\"f10\":1.77,\"f11\":0.35,\"f12\":\"300364\",\"f13\":0,\"f14\":\"中文在线\",\"f15\":11.87,\"f16\":10.17,\"f17\":10.2,\"f18\":10.3,\"f20\":8387510203,\"f21\":7410584854,\"f22\":0.0,\"f23\":5.32,\"f24\":-7.24,\"f25\":-23.13,\"f62\":41666736.0,\"f115\":76.76,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":15.89,\"f3\":10.27,\"f4\":1.48,\"f5\":328797,\"f6\":513359088.0,\"f7\":15.68,\"f8\":8.7,\"f9\":46.98,\"f10\":1.3,\"f11\":0.44,\"f12\":\"300179\",\"f13\":0,\"f14\":\"四方达\",\"f15\":16.68,\"f16\":14.42,\"f17\":14.42,\"f18\":14.41,\"f20\":7753398364,\"f21\":6006773187,\"f22\":0.06,\"f23\":7.9,\"f24\":55.94,\"f25\":45.65,\"f62\":-23881127.0,\"f115\":70.05,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2},{\"f1\":2,\"f2\":3.03,\"f3\":10.18,\"f4\":0.28,\"f5\":155959,\"f6\":46027902.0,\"f7\":10.55,\"f8\":1.87,\"f9\":-62.18,\"f10\":1.48,\"f11\":0.0,\"f12\":\"002502\",\"f13\":0,\"f14\":\"鼎龙文化\",\"f15\":3.03,\"f16\":2.74,\"f17\":2.75,\"f18\":2.75,\"f20\":2787583756,\"f21\":2523304808,\"f22\":0.0,\"f23\":2.12,\"f24\":-12.17,\"f25\":-30.34,\"f62\":16770230.0,\"f115\":-523.76,\"f128\":\"-\",\"f140\":\"-\",\"f141\":\"-\",\"f136\":\"-\",\"f152\":2}]}})"));
    }

    @Test
    public void testDailyImportByPageNumber() {
        stockService.scrapyDailyStockInfoByPageNumber(231, 300);
    }

    @Test
    public void testDailyImport() {
        stockService.scrapyDailyStockInfo();
    }

    @Test
    public void testGetStock() {
        stockService.getStockHist(0, "002756");
    }

    @Test
    public void testGetAllHist() {
        stockService.getAllHist(1000, 0);
        stockService.getAllHist(1000, 1);
        stockService.getAllHist(1000, 2);
//        stockService.getAllHist(1000, 3);
//        stockService.getAllHist(1000, 4);


        List<Integer> l = new ArrayList<>();
        int[] x = l.stream().mapToInt(Integer::intValue).toArray();
    }

    static int[] pas;
    static int[] vals;
    static int n;
    static int[] maxes;

    public static int bestSumDownwardTreePath(int[] parent, int[] values) {
        // Write your code here
        n = parent.length;
        pas = parent;
        vals = values;
        // max = Integer.MIN_VALUE;
//        pas = parent.stream() .mapToInt(Integer::intValue) .toArray();
//        vals = values.stream() .mapToInt(Integer::intValue) .toArray();

        maxes = new int[n];
        for (int i = 0; i < n; i++)
            maxes[i] = Integer.MIN_VALUE;
        return find(0);
    }

    public static int find(int idx) {
        int max = vals[idx];
        for (int i = 0; i < n; i++) {
            if (pas[i] == idx) {
                int mx = maxes[idx] == Integer.MIN_VALUE ? find(i) : maxes[idx];
                max = Math.max(max, vals[idx] + mx);
                max = Math.max(max, mx);
            }
        }
        maxes[idx] = max;
        return max;
    }

    public static int getMaxBarrier(List<Integer> initialEnergy, long th) {
        // Write your code here
//        int[] ie = initialEnergy.stream() .mapToInt(Integer::intValue) .toArray();
        int b = (int) initialEnergy.stream().mapToDouble(Integer::doubleValue).average().orElse(0D);
        List<Long> ie = initialEnergy.stream().map(Integer::longValue).collect(Collectors.toList());
        for (int i = b; i <= Integer.MAX_VALUE; i++) {
            int finalI = i;
            ie = ie.stream().filter(x -> x > 0).filter(x -> x - finalI > 0).collect(Collectors.toList());
            if (initialEnergy.stream().mapToLong(x -> x - finalI).sum() < th) return i - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
//        System.out.println(getMaxBarrier(Arrays.stream(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}).boxed().collect(Collectors.toList()), 2L));
//        System.out.println(bestSumDownwardTreePath(new int[]{-1, 0, 1, 2, 0}, new int[]{-2, 10, 10, -3, 10}));
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(bestSumDownwardTreePath(new int[]{}, new int[]{}));
    }
}
