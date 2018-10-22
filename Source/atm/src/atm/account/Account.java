package atm.account;

import java.math.BigInteger;

public abstract class Account {
	private BigInteger num;
	private BigInteger cardNum;
	private String type;
	private String ownerName;
	private float balance;
	protected CheckingAccount ca = new CheckingAccount();
	protected SavingAccount sa = new SavingAccount();
	protected MoneyMarketAccount mma = new MoneyMarketAccount();
	
	public BigInteger getNum() {
		return num;
	}

	public void setNum(BigInteger num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public BigInteger getCardNum() {
		return cardNum;
	}

	public void setCardNum(BigInteger cardNum) {
		this.cardNum = cardNum;
	}
	
	public static String getAccType(int accInd) {
		if (accInd == 1) {
			return "ca";
		} else if (accInd == 2) {
			return "sa";
		} else if (accInd == 3) {
			return "mma";
		}

		return null;
	}

}