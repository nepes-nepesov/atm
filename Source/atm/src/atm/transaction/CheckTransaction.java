package atm.transaction;

import java.sql.Date;
import java.sql.SQLException;
import atm.Card;
import atm.account.Account;

public class CheckTransaction extends Transaction {
	
	
	public CheckTransaction() throws ClassNotFoundException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOwnerName() throws SQLException {
		String name = null;
		
		resultSet = statement.executeQuery("SELECT owner_name FROM card WHERE number=" + Card.getNum());
		
		while (resultSet.next()) {
			name = resultSet.getString("owner_name");
		}
		
		setName("Check Owner Name");
		setAmount(0);
		setSucceeded(true);
		
		return name;
	}
	
	public String getBankName() throws SQLException {
		String name = null;
		
		resultSet = statement.executeQuery("SELECT bank_name FROM card WHERE number=" + Card.getNum());
		
		while (resultSet.next()) {
			name = resultSet.getString("bank_name");
		}
		
		setName("Check Bank Name");
		setAmount(0);
		setSucceeded(true);
		
		return name;
	}
	
	public Date getExpDate() throws SQLException {
		Date date = null;
		
		resultSet = statement.executeQuery("SELECT exp_date FROM card WHERE number=" + Card.getNum());
		
		while (resultSet.next()) {
			date = resultSet.getDate("exp_date");
		}
		
		setName("Check Date");
		setAmount(0);
		setSucceeded(true);
		
		return date;
	}
	
	public int getPin() throws ClassNotFoundException, SQLException {
		
		int pin = 0;
		
		resultSet = statement.executeQuery("SELECT pin FROM card WHERE number=" + Card.getNum());
		
		while (resultSet.next()) {
			pin = resultSet.getInt("pin");
		}
		
		setName("Check Pin");
		setAmount(0);
		setSucceeded(true);
		
		return pin;
	}
	
	public float checkBalance(int accInd) throws ClassNotFoundException, SQLException {

		float balance = 0;
		resultSet = statement.executeQuery("SELECT balance FROM account WHERE cardNum=" + Card.getNum()
				+ " AND type='" + Account.getAccType(accInd) + "'");

		while (resultSet.next()) {
			balance = resultSet.getFloat("balance");
		}
		
		setName("Balance Inquiry");
		setAmount(balance);
		setSucceeded(true);
		
		return balance;
	}
	
	public float getInterest(int accInd, float rate) throws ClassNotFoundException, SQLException {
		double principal = (new CheckTransaction()).checkBalance(accInd);
		int term = 1;
		int compFreq = 12;
		float calAmount = (float) (principal * Math.pow(1 + rate / compFreq, compFreq * term));
		setName("Calculate Interest");
		setAmount((float) calAmount);
		setSucceeded(true);
		return calAmount;
	}
}
