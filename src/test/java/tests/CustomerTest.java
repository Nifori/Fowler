package tests;

import model.Customer;
import model.Movie;
import model.Rental;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;


class CustomerTest {

    public static final String TITEL = "Titel";
    public static final String TEST_NAME = "testName";

    private Customer underTest;

    @Test
    void testAmountPriceCodeChildrenAbove3() {
        Movie testMovie = new Movie(TITEL, Movie.CHILDRENS);
        Rental testRental = new Rental(testMovie, 5);

        underTest = new Customer(TEST_NAME);
        underTest.addRental(testRental);

        double amount = underTest.amountFor(testRental);

        assertThat(amount, CoreMatchers.is(4.5));
    }

    @Test
    void testAmountPriceCodeNewReleaseAbove3() {
        Movie testMovie = new Movie(TITEL, Movie.NEW_RELEASE);
        Rental testRental = new Rental(testMovie, 5);

        underTest = new Customer(TEST_NAME);
        underTest.addRental(testRental);

        double amount = underTest.amountFor(testRental);

        assertThat(amount, CoreMatchers.is(15.0));
    }

    @Test
    void testAmountPriceCodeRegularAbove3() {
        Movie testMovie = new Movie(TITEL, Movie.REGULAR);
        Rental testRental = new Rental(testMovie, 5);

        underTest = new Customer(TEST_NAME);
        underTest.addRental(testRental);

        double amount = underTest.amountFor(testRental);

        assertThat(amount, CoreMatchers.is(6.5));
    }

    @Test
    void testAmountPriceCodeChildrenBelow3() {
        Movie testMovie = new Movie(TITEL, Movie.CHILDRENS);
        Rental testRental = new Rental(testMovie, 2);

        underTest = new Customer(TEST_NAME);
        underTest.addRental(testRental);

        double amount = underTest.amountFor(testRental);

        assertThat(amount, CoreMatchers.is(1.5));
    }

    @Test
    void testAmountPriceCodeNewReleaseBelow3() {
        Movie testMovie = new Movie(TITEL, Movie.NEW_RELEASE);
        Rental testRental = new Rental(testMovie, 2);

        underTest = new Customer(TEST_NAME);
        underTest.addRental(testRental);

        double amount = underTest.amountFor(testRental);

        assertThat(amount, CoreMatchers.is(6.0));
    }

    @Test
    void testAmountPriceCodeRegularBelow3() {
        Movie testMovie = new Movie(TITEL, Movie.REGULAR);
        Rental testRental = new Rental(testMovie, 2);

        underTest = new Customer(TEST_NAME);
        underTest.addRental(testRental);

        double amount = underTest.amountFor(testRental);

        assertThat(amount, CoreMatchers.is(2.0));
    }

    @Test
    void testCustomerStatement() {
        Movie testMovie = new Movie(TITEL, Movie.REGULAR);
        Rental testRental = new Rental(testMovie, 2);

        underTest = new Customer(TEST_NAME);
        underTest.addRental(testRental);

        String statement = underTest.statement();
        String expectedStatement = "model.Rental Record for testName\n\tTitle\t\tDays\tAmount\n\tTitel\t\t2\t2.0\nAmount owed is 2.0\nYou earned 1 frequent renter points";

        assertThat(statement, CoreMatchers.is(expectedStatement));
    }

}
