import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class TransactionTypeSearchTest {

  @Test
  public void testSingleSearchType() {
    List<Transaction> transactions = getStandardTransactions();
    List<Transaction> foundTransactions = new TransactionTypeSearch().findTransactions(transactions, TransactionType.IT);

    // Check that the filter retrieves the correct transactions and maintains
    // the order
    assertEquals(2, foundTransactions.size());
    assertEquals(transactions.get(2), foundTransactions.get(0));
    assertEquals(transactions.get(5), foundTransactions.get(1));
  }

  @Test
  public void testMultipleSearchTypes() {
    List<Transaction> transactions = getStandardTransactions();
    List<Transaction> foundTransactions = new TransactionTypeSearch().findTransactions(transactions, TransactionType.IT, TransactionType.FT);

    // Check that the filter retrieves the correct transactions and maintains
    // the order
    assertEquals(4, foundTransactions.size());
    assertEquals(transactions.get(1), foundTransactions.get(0));
    assertEquals(transactions.get(2), foundTransactions.get(1));
    assertEquals(transactions.get(4), foundTransactions.get(2));
    assertEquals(transactions.get(5), foundTransactions.get(3));
  }

  @Test
  public void testArrayOfSearchType() {
    List<Transaction> transactions = getStandardTransactions();
    List<Transaction> foundTransactions = new TransactionTypeSearch().findTransactions(transactions, new TransactionType[] { TransactionType.IT, TransactionType.FT });

    // Check that the filter retrieves the correct transactions and maintains
    // the order
    assertEquals(4, foundTransactions.size());
    assertEquals(transactions.get(1), foundTransactions.get(0));
    assertEquals(transactions.get(2), foundTransactions.get(1));
    assertEquals(transactions.get(4), foundTransactions.get(2));
    assertEquals(transactions.get(5), foundTransactions.get(3));
  }

  @Test
  public void testNullTransactionList() {
    List<Transaction> foundTransactions = new TransactionTypeSearch().findTransactions(null, TransactionType.IT);
    assertEquals(0, foundTransactions.size());
  }

  @Test
  public void testNullTransaction() {
    List<Transaction> transactions = new ArrayList<Transaction>();
    transactions.add(null);
    List<Transaction> foundTransactions = new TransactionTypeSearch().findTransactions(transactions, TransactionType.IT);
    assertEquals(0, foundTransactions.size());
  }

  @Test
  public void testEmptySearchTypes() {
    List<Transaction> transactions = getStandardTransactions();
    List<Transaction> foundTransactions = new TransactionTypeSearch().findTransactions(transactions);
    assertEquals(0, foundTransactions.size());
  }

  @Test
  public void testNullSearchType() {
    List<Transaction> transactions = getStandardTransactions();
    TransactionType type = null;
    List<Transaction> foundTransactions = new TransactionTypeSearch().findTransactions(transactions, type);
    assertEquals(0, foundTransactions.size());
  }

  @Test
  public void testNullTypeInTransaction() {
    List<Transaction> transactions = new ArrayList<Transaction>();
    transactions.add(new Transaction(null, 1000, new Date(), "description"));
    List<Transaction> foundTransactions = new TransactionTypeSearch().findTransactions(transactions, TransactionType.BP);
    assertEquals(0, foundTransactions.size());
  }

  @Test
  public void testSearchForNullTransactions() {
    List<Transaction> transactions = new ArrayList<Transaction>();
    transactions.add(new Transaction(null, 1000, new Date(), "description"));
    TransactionType type = null;
    List<Transaction> foundTransactions = new TransactionTypeSearch().findTransactions(transactions, type);
    assertEquals(1, foundTransactions.size());
  }

  /**
   * Convenience method for getting a standard list of transactions
   * 
   * @return
   */
  private List<Transaction> getStandardTransactions() {
    List<Transaction> transactions = new ArrayList<Transaction>();
    transactions.add(new Transaction(TransactionType.BP, 1000, new Date(), "description"));
    transactions.add(new Transaction(TransactionType.FT, 1000, new Date(), "description"));
    transactions.add(new Transaction(TransactionType.IT, 1000, new Date(), "description"));
    transactions.add(new Transaction(TransactionType.BP, 1000, new Date(), "description"));
    transactions.add(new Transaction(TransactionType.FT, 1000, new Date(), "description"));
    transactions.add(new Transaction(TransactionType.IT, 1000, new Date(), "description"));
    return transactions;
  }
}
