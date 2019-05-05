package model;

public class Movie {

    private String title;
    private PriceCode priceCode;

    public Movie(String newtitle, PriceCode newpriceCode) {
        title = newtitle;
        priceCode = newpriceCode;
    }

    public PriceCode getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(PriceCode arg) {
        priceCode = arg;
    }

    public String getTitle (){
        return title;
    }

}