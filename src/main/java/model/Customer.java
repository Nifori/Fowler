package model;

import Exceptions.InvalidPriceCodeException;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String newname) {
        name = newname;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("model.Rental Record for ");
        resultBuilder.append(this.getName());
        resultBuilder.append("\n");

        resultBuilder.append("\t");
        resultBuilder.append("Title");
        resultBuilder.append("\t");
        resultBuilder.append("\t");
        resultBuilder.append("Days");
        resultBuilder.append("\t");
        resultBuilder.append("Amount");
        resultBuilder.append("\n");

        for (Rental each : rentals) {
            double thisAmount = 0;
            //determine amounts for each line
            thisAmount = amountFor(each);
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints++;
            //show figures for this rental

            resultBuilder.append("\t");
            resultBuilder.append(each.getMovie().getTitle());
            resultBuilder.append("\t");
            resultBuilder.append("\t");
            resultBuilder.append(each.getDaysRented());
            resultBuilder.append("\t");
            resultBuilder.append(String.valueOf(thisAmount));
            resultBuilder.append("\n");

            totalAmount += thisAmount;
        }

        //add footer lines

        resultBuilder.append("Amount owed is ");
        resultBuilder.append(String.valueOf(totalAmount));
        resultBuilder.append("\n");

        resultBuilder.append("You earned ");
        resultBuilder.append(String.valueOf(frequentRenterPoints));
        resultBuilder.append(" frequent renter points");

        return resultBuilder.toString();
    }

    public double amountFor(Rental each) {
        double thisAmount = 0;
        switch (each.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (each.getDaysRented() > 2)
                    thisAmount += (each.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += each.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (each.getDaysRented() > 3)
                    thisAmount += (each.getDaysRented() - 3) * 1.5;
                break;
            default:
                throw new InvalidPriceCodeException();
        }
        return thisAmount;
    }

}
    