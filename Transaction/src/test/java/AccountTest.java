import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class AccountTest {

  // delta for comparing doubles
  private double delta = 0.00001;

  @Test
  public void testBalance() {
    Account account = new Account(5000);
    assertEquals(5000, account.getBalance(), delta);
    assertEquals(0, account.getTransactions().size());
  }

  @Test
  public void testNegativeBalance() {
    Account account = new Account(-5000);
    assertEquals(-5000, account.getBalance(), delta);
    assertEquals(0, account.getTransactions().size());
  }

  @Test
  public void testPositiveTransaction() {
    Account account = new Account(1000);
    account.processTransaction(new Transaction(TransactionType.IT, 2000, new Date(), "description"));
    assertEquals(3000, account.getBalance(), delta);
    assertEquals(1, account.getTransactions().size());
  }

  @Test
  public void testNegativeTransaction() {
    Account account = new Account(1000);
    account.processTransaction(new Transaction(TransactionType.IT, -2000, new Date(), "description"));
    assertEquals(-1000, account.getBalance(), delta);
    assertEquals(1, account.getTransactions().size());
  }

  @Test
  public void testMultipleTransactions() {

    List<Transaction> transactions = new ArrayList<Transaction>();
    transactions.add(new Transaction(TransactionType.IT, 2000, new Date(), "description"));
    transactions.add(new Transaction(TransactionType.IT, -3000, new Date(), "description"));
    transactions.add(new Transaction(TransactionType.IT, -2000, new Date(), "description"));
    transactions.add(new Transaction(TransactionType.IT, 1000, new Date(), "description"));

    Account account = new Account(1000);

    // Process the transactions
    account.processTransaction(transactions.get(0));
    account.processTransaction(transactions.get(1));
    account.processTransaction(transactions.get(2));
    account.processTransaction(transactions.get(3));

    // Check the balance
    assertEquals(-1000, account.getBalance(), 0);

    // Check that the account stores the transactions in the correct order
    List<Transaction> accountTransactions = account.getTransactions();
    assertEquals(4, accountTransactions.size());
    assertEquals(transactions.get(0), accountTransactions.get(0));
    assertEquals(transactions.get(1), accountTransactions.get(1));
    assertEquals(transactions.get(2), accountTransactions.get(2));
    assertEquals(transactions.get(3), accountTransactions.get(3));

  }

  @Test
  public void testNullTransaction() {
    Account account = new Account(1000);
    account.processTransaction(null);
    assertEquals(1000, account.getBalance(), delta);
    assertEquals(0, account.getTransactions().size());
  }

}
