package model;

import exceptions.InvalidPriceCodeException;

import java.util.ArrayList;
import java.util.List;

import static model.PriceCode.NEW_RELEASE;

public class Customer {

    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String newName) {
        name = newName;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Rental Record for ");
        resultBuilder.append(this.name);
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

            //determine amounts for each line
            double thisAmount = amountFor(each);

            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints++;
            //show figures for this rental

            resultBuilder.append("\t");
            resultBuilder.append(each.getMovie().getTitle());
            resultBuilder.append("\t");
            resultBuilder.append("\t");
            resultBuilder.append(each.getDaysRented());
            resultBuilder.append("\t");
            resultBuilder.append(thisAmount);
            resultBuilder.append("\n");

            totalAmount += thisAmount;
        }

        //add footer lines

        resultBuilder.append("Amount owned is ");
        resultBuilder.append(totalAmount);
        resultBuilder.append("\n");

        resultBuilder.append("You earned ");
        resultBuilder.append(frequentRenterPoints);
        resultBuilder.append(" frequent renter points");

        return resultBuilder.toString();
    }

    public double amountFor(Rental each) {
        double thisAmount = 0;
        switch (each.getMovie().getPriceCode()) {
            case REGULAR:
                thisAmount += 2;
                if (each.getDaysRented() > 2)
                    thisAmount += (each.getDaysRented() - 2) * 1.5;
                break;
            case NEW_RELEASE:
                thisAmount += each.getDaysRented() * 3;
                break;
            case CHILDREN:
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
    