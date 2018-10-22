package atm.account;

import java.math.BigInteger;

public class CheckingAccount extends Account {
	private float balance = 980.15f;
	private BigInteger number = new BigInteger("10134654796564");
	
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
