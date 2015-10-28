/**
 * Enum to store the various possible transaction types. It contains the code
 * and description of a transaction. Eg code BP and description Bill payment.
 * 
 * @author Steven
 * 
 */
public enum TransactionType {

  BP("Bill payment"), FT("Funds transfer"), IT("International transfer");

  private String description;

  TransactionType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}