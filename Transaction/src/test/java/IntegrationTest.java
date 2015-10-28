import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class IntegrationTest {

  // delta for comparing doubles
  private double delta = 0.00001;

  // Test to test and demonstrate how the various classes can work together
  @Test
  public void testIntegration() throws ParseException {
    FileProcessor controller = new FileProcessor();
    Account account = controller.readFile(FileProcessorTest.class.getResource("valid.txt").getFile());

    // Check the balance
    assertEquals(3000, account.getBalance(), delta);
    List<Transaction> transactions = account.getTransactions();
    assertEquals(5, transactions.size());

    // Check filtering based on type
    List<Transaction> typeFilteredTransactions = new TransactionTypeSearch().findTransactions(transactions, TransactionType.BP);
    assertEquals(1, typeFilteredTransactions.size());

    // Check filtering based on date
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date from = formatter.parse("04/08/2015");
    Date to = formatter.parse("06/08/2015");

    List<Transaction> dateFilteredTransactions = new DateRangeSearch().findTransactions(transactions, from, to);
    assertEquals(3, dateFilteredTransactions.size());

  }
}
