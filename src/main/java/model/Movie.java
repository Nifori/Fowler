package model;

public class Movie {

    private final String title;
    private final PriceCode priceCode;

    public Movie(String newTitle, PriceCode newPriceCode) {
        title = newTitle;
        priceCode = newPriceCode;
    }

    public PriceCode getPriceCode() {
        return priceCode;
    }

    public String getTitle (){
        return title;
    }

}