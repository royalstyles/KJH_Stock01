package com.example.kjh_stock01.ui.dashboard.stock;

public class Stock {

    private static String apikey = "c627830f0b7f8b7010fbb9fe63f677c8";

    private String symbol;
    private String name;
    private String change;
    private String price;
    private String changesPercentage;

    public Stock(String symbol, String name, String change, String price, String changesPercentage) {
        this.symbol = symbol;
        this.name = name;
        this.change = change;
        this.price = price;
        this.changesPercentage = changesPercentage;
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

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChangesPercentage() {
        return changesPercentage;
    }

    public void setChangesPercentage(String changesPercentage) {
        this.changesPercentage = changesPercentage;
    }

    public static String getApikey() {
        return apikey;
    }
}
