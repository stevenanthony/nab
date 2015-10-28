import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class FileProcessorTest {

  @Test
  public void testValid() throws ParseException {

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    FileProcessor controller = new FileProcessor();
    Account account = controller.readFile(getFile("valid.txt"));
    assertEquals(3000, account.getBalance(), 0);
    assertEquals(5, account.getTransactions().size());
    Transaction transaction = account.getTransactions().get(0);
    assertEquals(500, transaction.getAmount(), 0);
    assertEquals(TransactionType.FT, transaction.getType());
    assertEquals(formatter.parse("03/08/2015"), transaction.getDate());
    assertEquals("salary", transaction.getDescription());
  }

  @Test
  public void testNoTransactions() {

    FileProcessor controller = new FileProcessor();
    Account account = controller.readFile(getFile("notransactions.txt"));
    assertEquals(-1000, account.getBalance(), 0);
    assertEquals(0, account.getTransactions().size());
  }

  @Test
  public void testNoAccount() {

    FileProcessor controller = new FileProcessor();
    Account account = controller.readFile(getFile("noaccount.txt"));
    assertNull(account);
  }

  @Test
  public void testInvalidAccount() {
    FileProcessor controller = new FileProcessor();
    Account account = controller.readFile(getFile("invalidaccount.txt"));
    assertNull(account);
  }

  @Test
  public void testAllInvalidTransactions() {

    FileProcessor controller = new FileProcessor();
    Account account = controller.readFile(getFile("invalidtransactions.txt"));
    assertEquals(1000, account.getBalance(), 0);
    assertEquals(0, account.getTransactions().size());
  }

  @Test
  public void testSomeInvalidTransactions() {

    FileProcessor controller = new FileProcessor();
    Account account = controller.readFile(getFile("someinvalidtransactions.txt"));
    assertEquals(2000, account.getBalance(), 0);
    assertEquals(2, account.getTransactions().size());
  }

  @Test
  public void testNoFile() {
    FileProcessor controller = new FileProcessor();
    Account account = controller.readFile("");
    assertNull(account);
  }

  private String getFile(String filename) {
    return FileProcessorTest.class.getResource(filename).getFile();
  }
}
