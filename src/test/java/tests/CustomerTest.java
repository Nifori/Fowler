package tests;

import exceptions.InvalidPriceCodeException;
import model.Customer;
import model.Movie;
import model.PriceCode;
import model.Rental;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;


class CustomerTest {

    private static final String TITEL = "Titel";
    private static final String TEST_NAME = "testName";

    private Customer underTest;

    @Test
    void testAmountPriceCodeChildrenAbove3() {
        Movie testMovie = new Movie(TITEL, PriceCode.CHILDREN);
        Rental testRental = new Rental(testMovie, 5);

        underTest = new Customer(TEST_NAME);
        underTest.addRental(testRental);

        double amount = underTest.amountFor(testRental);

        assertThat(amount, CoreMatchers.is(4.5));
    }

    @Test
    void testAmountPriceCodeNewReleaseAbove3() {
        Movie testMovie = new Movie(TITEL, PriceCode.NEW_RELEASE);
        Rental testRental = new Rental(testMovie, 5);

        underTest = new Customer(TEST_NAME);
        underTest.addRental(testRental);

        double amount = underTest.amountFor(testRental);

        assertThat(amount, CoreMatchers.is(15.0));
    }

    @Test
    void testAmountPriceCodeRegularAbove3() {
        Movie testMovie = new Movie(TITEL, PriceCode.REGULAR);
        Rental testRental = new Rental(testMovie, 5);

        underTest = new Customer(TEST_NAME);
        underTest.addRental(testRental);

        double amount = underTest.amountFor(testRental);

        assertThat(amount, CoreMatchers.is(6.5));
    }

    @Test
    void testAmountPriceCodeChildrenBelow3() {
        Movie testMovie = new Movie(TITEL, PriceCode.CHILDREN);
        Rental testRental = new Rental(testMovie, 2);

        underTest = new Customer(TEST_NAME);
        underTest.addRental(testRental);

        double amount = underTest.amountFor(testRental);

        assertThat(amount, CoreMatchers.is(1.5));
    }

    @Test
    void testAmountPriceCodeNewReleaseBelow3() {
        Movie testMovie = new Movie(TITEL, PriceCode.NEW_RELEASE);
        Rental testRental = new Rental(testMovie, 2);

        underTest = new Customer(TEST_NAME);
        underTest.addRental(testRental);

        double amount = underTest.amountFor(testRental);

        assertThat(amount, CoreMatchers.is(6.0));
    }

    @Test
    void testAmountPriceCodeRegularBelow3() {
        Movie testMovie = new Movie(TITEL, PriceCode.REGULAR);
        Rental testRental = new Rental(testMovie, 2);

        underTest = new Customer(TEST_NAME);
        underTest.addRental(testRental);

        double amount = underTest.amountFor(testRental);

        assertThat(amount, CoreMatchers.is(2.0));
    }

    @Test
    void testCustomerStatement() {
        Movie testMovie = new Movie(TITEL, PriceCode.REGULAR);
        Rental testRental = new Rental(testMovie, 2);

        underTest = new Customer(TEST_NAME);
        underTest.addRental(testRental);

        String statement = underTest.statement();
        String expectedStatement = "Rental Record for testName\n\tTitle\t\tDays\tAmount\n\tTitel\t\t2\t2.0\nAmount owned is 2.0\nYou earned 1 frequent renter points";

        assertThat(statement, CoreMatchers.is(expectedStatement));
    }

    @Test
    void testInvalidPriceCodeExceptionGetsThrown() {
        Movie testMovie = new Movie(TITEL, PriceCode.INVALID);
        Rental testRental = new Rental(testMovie, 2);

        underTest = new Customer(TEST_NAME);

        Assertions.assertThrows(InvalidPriceCodeException.class, () -> underTest.amountFor(testRental));
    }

}
