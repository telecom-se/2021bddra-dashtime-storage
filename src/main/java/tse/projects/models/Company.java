package tse.projects.models;

public class Company extends DatabaseModel {
    public String symbol;
    public String name;
    public String description;
    public String currency;

    public Company(String symbol, String name, String description, String currency) {
        this.symbol = symbol;
        this.name = name;
        this.description = description;
        this.currency = currency;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
