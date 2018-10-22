package atm.account;

import java.math.BigInteger;

public class SavingAccount extends Account {
	private float balance = 10588.27f;
	private	BigInteger number = new BigInteger("30134654796589");
	
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
