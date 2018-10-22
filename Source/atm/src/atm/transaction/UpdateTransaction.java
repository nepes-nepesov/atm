package atm.transaction;

import java.sql.SQLException;

import atm.Card;
import atm.account.Account;

public class UpdateTransaction extends Transaction {
	
	public UpdateTransaction() throws ClassNotFoundException, SQLException {
		super();
	}

	public void withdraw(int accInd, float withAmount) throws SQLException, ClassNotFoundException {
		
		float curBalance = (new CheckTransaction()).checkBalance(accInd);
		float newBalance = curBalance - withAmount;
		preparedStatement.executeUpdate("UPDATE account SET balance=" + newBalance 
										+ "WHERE type='" + Account.getAccType(accInd) + "'"
										+ "AND cardNum=" + Card.getNum());
		
		newBalance = (new CheckTransaction()).checkBalance(accInd);
		
		setName("Withdraw");
		setAmount(withAmount);
		setSucceeded(true);
		System.out.println("Withdrawal OK. Please take your money: " + withAmount);
		System.out.println("Your new balance: " + newBalance);
	}
	
	public void deposit(int accInd, float depAmount) throws ClassNotFoundException, SQLException {
		float curBalance = (new CheckTransaction()).checkBalance(accInd);
		float newBalance = curBalance + depAmount;
		preparedStatement.executeUpdate("UPDATE account SET balance=" + newBalance 
										+ "WHERE type='" + Account.getAccType(accInd) + "'"
										+ "AND cardNum=" + Card.getNum());
		
		newBalance = (new CheckTransaction()).checkBalance(accInd);
		
		System.out.println("Deposit OK. Amount: " + depAmount);
		System.out.println("Your new balance: " + newBalance);
		setName("Deposit");
		setAmount(depAmount);
		setSucceeded(true);
	}
	
}