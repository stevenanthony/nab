import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class to filter transactions based on a date range
 * 
 * @author Steven
 * 
 */
public class DateRangeSearch {

  /**
   * Method to return list of transactions that fall within a date range
   * 
   * @param transactions
   * @param from
   * @param to
   * @return
   */
  public List<Transaction> findTransactions(List<Transaction> transactions, Date from, Date to) {
    List<Transaction> list = new ArrayList<Transaction>();

    if (transactions != null && from != null && to != null) {
      // Loop through the transactions
      for (Transaction transaction : transactions) {
        if (transactionWithinDateRange(transaction, from, to)) {
          list.add(transaction);
        }
      }
    }

    return list;
  }

  /**
   * Convenience method to check if a transaction falls within dates
   * 
   * @param transaction
   * @param from
   * @param to
   * @return
   */
  private boolean transactionWithinDateRange(Transaction transaction, Date from, Date to) {
    if (transaction == null || transaction.getDate() == null) {
      return false;
    }

    // From is on or before date and to is after or on date
    if (from.compareTo(transaction.getDate()) <= 0 && to.compareTo(transaction.getDate()) >= 0) {
      return true;
    }

    return false;

  }

}
