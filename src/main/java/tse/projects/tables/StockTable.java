package tse.projects.tables;

import tse.projects.models.Stock;

import java.util.List;

public class StockTable extends DatabaseTable {

    public void addStocks(List<Stock> stocks) {
        this.items.addAll(stocks);
    }

}
