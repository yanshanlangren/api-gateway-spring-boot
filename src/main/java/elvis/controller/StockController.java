package elvis.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import elvis.bo.Stock;
import elvis.controller.vo.req.StockQuery;
import elvis.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/daily")
    public ResponseEntity<Integer> daily() {
        return ResponseEntity.ok(stockService.scrapyDailyStockInfo());
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Stock>> list(StockQuery query) {
        return ResponseEntity.ok(stockService.listPage(query));
    }
}
