import java.util.Date;

/**
 * Class to store the details of a transaction
 * 
 * @author Steven
 * 
 */
public class Transaction {

  private TransactionType type;
  private double amount;
  private Date date;
  private String description;

  public Transaction(TransactionType type, double amount, Date date, String description) {
    super();
    this.type = type;
    this.amount = amount;
    this.date = date;
    this.description = description;
  }

  public TransactionType getType() {
    return type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
