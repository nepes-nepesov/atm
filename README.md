# ATM
ATM simulator

## How To Use

For the testing purpose you should use pin = 1006 because it's assumed that the inserted card has number 6502000305079895. The program accepts only three different trials for pin input. If none of your pin is valid after three attempts, then you’re the software will simulate blocking of the card.

After a successful pin code, four main types of operations are presented:
1. Balance Inquiry
2. Withdraw
3. Deposit
4. Calculate Interest rate.

Balance inquiry, Withdraw and Deposit have the same sub options such as:
1. Checking account
2. Savings account
3. Money Market account

Based on your choice of accounts (Saving account or money Market account), you will be able to see how much you have withdrawn or deposited.

The interest rate is also related to the two different type of accounts (Saving or Money Market Account). The program gives you the annual interest rate of 1%, 2%, 5%, 10% depending on the one you are on.

The project uses MySQL database to compare and update data. To use the database, import atm.sql (or two .csv files) located in “Data” folder to your database schema and call it “atm”.
