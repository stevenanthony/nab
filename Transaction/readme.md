Notes:

This was built in Eclipse using jdk 1.7.0_11.

The src classes are in src/main/java. The Transaction class represents a Transaction, and the TransactionType is an enum which
stores the possible transaction types. The Account classes stores a list of transactions, and the account balance after the
transactions have been applied. The FileProcessor class reads in an account balance and transactions from a file, and returns 
an account, to allow retrieving the balance and a list of transactions.

The DateRangeSearch class searches a list of transactions for transactions within a date range, and the TransactionTypeSearch 
class will search a list of transactions for transaction of a certain type (or types). 

The search classes are separate from the FileProcessor and Account classes, in order to allow chaining searches together 
(e.g. if you wanted to list all transactions of a certain type, within a date), as well as allowing adding new searches
(e.g. find all positive/negative transactions). Because of this, there is currently no "main" class that provides all the combined
functionality (i.e. read in a file, and provide methods to return the balance, list all transactions, and find transactions by 
date range and transaction type). However, to demonstrate how such a class could work, I have added an IntegrationTest class
that chains the methods together, and tests the various functions.

The test classes are in src/test/java, and the FileProcessorTest (and IntegrationTest) makes use of the files in src/test/resources.    

Assumptions:

1. Transactions belong to an account. We will process the transactions on the account as soon as we read in the transactions
from the file. Transactions should be processed one at a time, instead of all at once.

2. The amount values (for balance and transaction amount) must start with a + or a -. We cannot default it to a positive 
transaction if it doesn't contain a +.

3. There is no requirement to round the input values to 2 decimal places.

4. The type of a transaction should never be null, but if it did happen, it might be useful to be able to search for 
transactions with a type of null.

5. When the task states to create one or more classes to provide the requested functionality, it does not require a "main"
class that provides a single interface for all the functionality.