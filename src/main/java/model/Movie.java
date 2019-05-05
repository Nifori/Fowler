package model;

public class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

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