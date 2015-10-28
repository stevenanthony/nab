import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Class to process a file and return details of the account and transactions
 * 
 * @author Steven
 * 
 */
public class FileProcessor {

  DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

  /**
   * Read the file and return an account based on the starting balance, and
   * transactions
   * 
   * @param fileName
   * @return
   */
  public Account readFile(String fileName) {
    Account account = null;
    Scanner scanner;

    try {
      scanner = new Scanner(new File(fileName));
    }
    catch (FileNotFoundException fnfe) {
      return account;
    }

    // Process the account first
    if (scanner.hasNextLine()) {
      String accountInput = scanner.nextLine();
      account = getAccount(accountInput);

      if (account != null) {

        // Process the transactions
        while (scanner.hasNextLine()) {
          String input = scanner.nextLine();
          Transaction transaction = getTransaction(input);
          if (transaction != null) {
            account.processTransaction(transaction);
          }
        }
      }
    }

    scanner.close();

    return account;
  }

  /**
   * Convenience method to get a transaction based on a line of input
   * 
   * @param transactionInput
   * @return
   */
  private Transaction getTransaction(String transactionInput) {

    if (transactionInput == null) {
      return null;
    }

    String[] inputArray = transactionInput.split(",");
    // Must have 4 fields, and the amount field is valid
    if (inputArray.length != 4 || (!validAmount(inputArray[0]))) {
      return null;
    }

    try {
      Double amount = Double.parseDouble(inputArray[0]);
      TransactionType type = TransactionType.valueOf(inputArray[1]);
      Date date = formatter.parse(inputArray[2]);
      String description = inputArray[3];

      if (amount != null && type != null && date != null && description != null) {
        return new Transaction(type, amount, date, description);
      }
      else {
        return null;
      }
    }
    catch (Exception e) {
      return null;
    }

  }

  /**
   * Convenience method to return an account based on a line of input
   * 
   * @param accountInput
   * @return
   */
  private Account getAccount(String accountInput) {
    // Is it a valid amount
    if (!validAmount(accountInput)) {
      return null;
    }

    try {
      return new Account(Double.parseDouble(accountInput));
    }
    catch (Exception e) {
      return null;
    }
  }

  /**
   * Convenience method to ensure that an amount is valid (ie starts with + or
   * -)
   * 
   * @param input
   * @return
   */
  private boolean validAmount(String input) {
    return input.startsWith("+") || input.startsWith("-");
  }
}
