package atm;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;

import atm.transaction.CheckTransaction;

public class Card {

	public Card(BigInteger cardNum) {
		num = cardNum;
	}
	
	private static BigInteger num;
	private String ownerName;
	private Date expDate;
	private int pin;
	private String bankName;

	public String getOwnerName() throws ClassNotFoundException, SQLException {
		ownerName = (new CheckTransaction()).getOwnerName();
		return ownerName;
	}
	
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	public static BigInteger getNum() {
		return num;
	}

	public Date getExpDate() throws ClassNotFoundException, SQLException{
		expDate = (new CheckTransaction()).getExpDate();
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getBankName() throws ClassNotFoundException, SQLException {
		bankName =  (new CheckTransaction()).getBankName();
		return bankName;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public int getPin() throws ClassNotFoundException, SQLException {
		pin = (new CheckTransaction()).getPin();
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

}