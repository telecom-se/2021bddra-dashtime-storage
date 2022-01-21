package tse.projects;

import tse.projects.models.Company;
import tse.projects.models.DatabaseModel;
import tse.projects.models.Stock;

import java.util.ArrayList;
import java.util.List;

public class FakerData {

    public static List<Company> getFakeCompanies() {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company("AAPL", "apple", "Apple Inc.", "USD"));
        companies.add(new Company("ABNB", "airbnb", "Airbnb, Inc.", "USD"));
        companies.add(new Company("AMZN", "amazon", "Amazon.com, Inc.", "USD"));
        companies.add(new Company("DIS", "disney", "The Walt Disney Company.", "USD"));
        companies.add(new Company("DELL", "dell", "Dell Technologies Inc.", "USD"));
        companies.add(new Company("FB", "facebook", "Ford Motor Company", "USD"));
        companies.add(new Company("F", "ford", "Fortinet, Inc.", "USD"));
        companies.add(new Company("GOOG", "google", "Alphabet Inc.", "USD"));
        companies.add(new Company("NFLX", "netflix", "Netflix, Inc.", "USD"));
        companies.add(new Company("NIKE", "nike", "Nike, Inc.", "USD"));
        companies.add(new Company("OVH.PA", "ovh", "OVH Groupe S.A.", "EUR"));
        companies.add(new Company("TSLA", "tesla", "Tesla, Inc.", "USD"));
        companies.add(new Company("ZM", "zoom", "Zoom Video Communications, Inc.", "USD"));
        return companies;
    }

    public static List<Stock> getFakeStocks(String symbol) {
        List<Stock> stocks = new ArrayList<>();
        double value = (Math.random() * 800) + Math.random() + 200;
        double fluctuation = value * 0.05;
        for (long i = 0; i < 1000; i++) {
            value += (Math.random() * fluctuation) - fluctuation / 2;
            stocks.add(new Stock(i + 1, symbol, 1640991600L + 3600 * i, value));
        }
        return stocks;
    }

}
