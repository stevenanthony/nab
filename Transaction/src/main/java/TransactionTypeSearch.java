import java.util.ArrayList;
import java.util.List;

/**
 * Class to filter transactions based on one or more transaction types.
 * 
 * @author Steven
 * 
 */
public class TransactionTypeSearch {

  /**
   * Returns a list of transactions that match the requested type(s). Note that
   * it uses varargs, to enable passing in one or many types
   * 
   * @param transactions
   * @param searchTypes The transaction types to match
   * @return
   */
  public List<Transaction> findTransactions(List<Transaction> transactions, TransactionType... searchTypes) {
    List<Transaction> list = new ArrayList<Transaction>();

    if (transactions != null && searchTypes != null) {
      // Loop through the transactions
      for (Transaction transaction : transactions) {
        if (transactionTypeInList(transaction, searchTypes)) {
          list.add(transaction);
        }
      }
    }

    return list;
  }

  /**
   * Convenience method to check if a transaction matches the given type(s)
   * 
   * @param transaction
   * @param searchTypes
   * @return
   */
  private boolean transactionTypeInList(Transaction transaction, TransactionType... searchTypes) {
    if (transaction == null) {
      return false;
    }

    // Loop through the searchTypes to see if the transaction matches
    for (TransactionType transactionType : searchTypes) {
      if (transactionType == transaction.getType()) {
        return true;
      }
    }

    return false;
  }
}
