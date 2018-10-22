package atm.account;

import java.math.BigInteger;

public class MoneyMarketAccount extends Account {
	private float balance = 4125.33f;
	private	BigInteger number = new BigInteger("20134654796566");
	
	public float getBalance() {
		return balance;
	}
	
	public void setBalance(float amount) {
		balance = amount;
	}
	
	protected BigInteger getNumber() {
		return number;
	}
}
