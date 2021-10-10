package pack.newbook.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StockState {

    private final Map<Integer, Stock> map = new HashMap<>();

    private StockState(List<Stock> stocks) {
        stocks.forEach(stock -> {
            map.put(stock.getRank(), stock);
        });
    }

    public static StockState of(List<Stock> stocks) {
        return new StockState(stocks);
    }

    public Stock getStockState(int rank) {
        return map.get(rank);
    }

}
