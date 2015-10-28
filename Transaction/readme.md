Notes:

This was built in Eclipse using jdk 1.7.0_11.

I created the filters as separate classes that take in a list of transactions, so they could be chained together.

I didn't see any requirements to have a main class, that would read in a file, and perform various actions on the transactions,
but I did create an example class, IntegrationTest, which tests (and demonstrates) how that would work.


Assumptions:

1. Transactions belong to an account. We will process the transactions on the account as soon as we read in the transactions
from the file. Transactions should be processed one at a time, instead of all at once.

2. The amount values (for balance and transaction amount) must start with a + or a -. We cannot default it to a positive 
transaction if it doesn't contain a +.

3. There is no requirement to round the input values to 2 decimal places.

4. The type of a transaction should never be null, but if it did happen, it might be useful to be able to search for 
transactions with a type of null.