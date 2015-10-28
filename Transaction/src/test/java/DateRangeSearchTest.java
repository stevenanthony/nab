import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class DateRangeSearchTest {

  // DateFormat for formatting dates
  private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

  @Test
  public void testInsideDateRange() throws ParseException {
    List<Transaction> transactions = getStandardTransactions();

    Date from = formatter.parse("27/10/2015");
    Date to = formatter.parse("30/10/2015");
    List<Transaction> foundTransactions = new DateRangeSearch().findTransactions(transactions, from, to);

    // Check that the filter retrieves the correct transactions and maintains
    // the order
    assertEquals(4, foundTransactions.size());
    assertEquals(transactions.get(1), foundTransactions.get(0));
    assertEquals(transactions.get(2), foundTransactions.get(1));
    assertEquals(transactions.get(3), foundTransactions.get(2));
    assertEquals(transactions.get(4), foundTransactions.get(3));
  }

  @Test
  public void testOutsideDateRange() throws ParseException {
    List<Transaction> transactions = getStandardTransactions();

    Date from = formatter.parse("24/10/2015");
    Date to = formatter.parse("25/10/2015");
    List<Transaction> foundTransactions = new DateRangeSearch().findTransactions(transactions, from, to);
    assertEquals(0, foundTransactions.size());
  }

  @Test
  public void testInvalidDateRange() throws ParseException {
    List<Transaction> transactions = getStandardTransactions();

    Date from = formatter.parse("25/10/2015");
    Date to = formatter.parse("24/10/2015");
    List<Transaction> foundTransactions = new DateRangeSearch().findTransactions(transactions, from, to);
    assertEquals(0, foundTransactions.size());
  }

  @Test
  public void testTransactionOnFromDate() throws ParseException {
    List<Transaction> transactions = getStandardTransactions();

    Date from = formatter.parse("25/10/2015");
    Date to = formatter.parse("26/10/2015");
    List<Transaction> foundTransactions = new DateRangeSearch().findTransactions(transactions, from, to);
    assertEquals(1, foundTransactions.size());
  }

  @Test
  public void testTransactionOnToDate() throws ParseException {
    List<Transaction> transactions = getStandardTransactions();

    Date from = formatter.parse("31/10/2015");
    Date to = formatter.parse("1/11/2015");
    List<Transaction> foundTransactions = new DateRangeSearch().findTransactions(transactions, from, to);
    assertEquals(1, foundTransactions.size());
  }

  @Test
  public void testTimes() throws ParseException {
    List<Transaction> transactions = getStandardTransactions();

    DateFormat timeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    Date from = timeFormatter.parse("26/10/2015 01:00:00");
    Date to = timeFormatter.parse("27/10/2015 01:00:00");
    List<Transaction> foundTransactions = new DateRangeSearch().findTransactions(transactions, from, to);
    assertEquals(1, foundTransactions.size());
  }

  @Test
  public void testNullTransactions() throws ParseException {
    Date from = formatter.parse("31/10/2015");
    Date to = formatter.parse("1/11/2015");
    List<Transaction> foundTransactions = new DateRangeSearch().findTransactions(null, from, to);
    assertEquals(0, foundTransactions.size());
  }

  @Test
  public void testNullTransaction() throws ParseException {
    List<Transaction> transactions = new ArrayList<Transaction>();
    transactions.add(null);

    Date from = formatter.parse("31/10/2015");
    Date to = formatter.parse("1/11/2015");
    List<Transaction> foundTransactions = new DateRangeSearch().findTransactions(transactions, from, to);
    assertEquals(0, foundTransactions.size());
  }

  @Test
  public void testNullTransactionDate() throws ParseException {
    List<Transaction> transactions = new ArrayList<Transaction>();
    transactions.add(new Transaction(TransactionType.IT, 500, null, "description"));

    Date from = formatter.parse("31/10/2015");
    Date to = formatter.parse("1/11/2015");
    List<Transaction> foundTransactions = new DateRangeSearch().findTransactions(transactions, from, to);
    assertEquals(0, foundTransactions.size());
  }

  @Test
  public void testNullFromDate() throws ParseException {
    List<Transaction> transactions = getStandardTransactions();

    Date to = formatter.parse("1/11/2015");
    List<Transaction> foundTransactions = new DateRangeSearch().findTransactions(transactions, null, to);
    assertEquals(0, foundTransactions.size());
  }

  @Test
  public void testNullToDate() throws ParseException {
    List<Transaction> transactions = getStandardTransactions();

    Date from = formatter.parse("31/10/2015");
    List<Transaction> foundTransactions = new DateRangeSearch().findTransactions(transactions, from, null);
    assertEquals(0, foundTransactions.size());
  }

  /**
   * Convenience method to get some standard transactions
   * 
   * @return
   * @throws ParseException
   */
  private List<Transaction> getStandardTransactions() throws ParseException {
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    List<Transaction> transactions = new ArrayList<Transaction>();

    transactions.add(new Transaction(TransactionType.BP, 1000, formatter.parse("26/10/2015"), "description"));
    transactions.add(new Transaction(TransactionType.BP, 1000, formatter.parse("27/10/2015"), "description"));
    transactions.add(new Transaction(TransactionType.BP, 1000, formatter.parse("28/10/2015"), "description"));
    transactions.add(new Transaction(TransactionType.BP, 1000, formatter.parse("29/10/2015"), "description"));
    transactions.add(new Transaction(TransactionType.BP, 1000, formatter.parse("30/10/2015"), "description"));
    transactions.add(new Transaction(TransactionType.BP, 1000, formatter.parse("31/10/2015"), "description"));

    return transactions;
  }

}
