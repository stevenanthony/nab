import java.util.ArrayList;
import java.util.List;

/**
 * Class to store the balance and transaction details for an account.
 * 
 * @author Steven
 * 
 */
public class Account {

  private double balance;
  private List<Transaction> transactions;

  public Account(double balance) {
    this.balance = balance;
    this.transactions = new ArrayList<Transaction>();
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  /**
   * Process a transaction. Update the balance and store it in the list
   * 
   * @param transaction
   */
  public void processTransaction(Transaction transaction) {
    if (transaction != null) {
      balance += transaction.getAmount();
      transactions.add(transaction);
    }
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

}
