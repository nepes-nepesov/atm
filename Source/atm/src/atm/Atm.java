package atm;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import atm.account.Account;
import atm.transaction.CheckTransaction;
import atm.transaction.Transaction;
import atm.transaction.UpdateTransaction;

import java.util.Date;
import java.sql.SQLException;

public class Atm {
	private static Scanner reader = new Scanner(System.in);
	private static Logger logger = Logger.getLogger("MyLog");
	private static final String NAME = "Bank of Francia, Stringfield street";
	private static final String ADDRESS = "102 Stringfield street";
	private static final String WEB_ADDRESS = "www.bf.fr";
	private static Transaction transaction;
	private static CheckTransaction ct;
	private static UpdateTransaction ut;
	private static Card card = new Card(new BigInteger("6502000305079895")); // assume this card is inserted into ATM
	private static final int ALLOWED_PIN_INPUT_COUNT = 3;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ct = new CheckTransaction();
		ut = new UpdateTransaction();

		try {
			FileHandler fh = new FileHandler(System.getProperty("user.dir") + "transactions.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 0. Expiry Date
		Date curDate = new Date();
		if (curDate.compareTo(card.getExpDate()) > 0) {
			System.out.println("Sorry, you card is expired");
			System.exit(0);
		}

		// 1. Pin
		int inputPin = getUserInput("Type PIN: ");
		int pinInputCount = 0;

		while (inputPin != card.getPin()) {
			pinInputCount++;

			if (pinInputCount >= getAllowedPinInputCount()) {
				System.out.println("Wrong PIN. Your card is blocked. Please contact the bank.");
				System.exit(0);
			}

			inputPin = getUserInput("Wrong PIN. Number of trials left: " + (getAllowedPinInputCount() - pinInputCount)
					+ ". Try again: ");
		}

		System.out.println("Pin succeeded!");

		// 2. Operations
		System.out.println("\nWhat would you like to do?");

		Map<Integer, String> keyMap = new HashMap<>();
		keyMap.put(1, "Balance inquiry");
		keyMap.put(2, "Withdraw");
		keyMap.put(3, "Deposit");
		keyMap.put(4, "Calculate interest");

		for (Map.Entry<Integer, String> entry : keyMap.entrySet()) {
			System.out.println(entry.getKey() + ". " + entry.getValue());
		}

		int inputOperation = getUserInput("Type a number: ");

		while (inputOperation != 1 && inputOperation != 2 && inputOperation != 3 && inputOperation != 4) {
			System.out.println("Incorrect input. Please type '1' or '2' or '3' or '4'.");
			inputOperation = getUserInput("Type a number: ");
		}

		// 2.a. Balance Inquiry
		if (keyMap.get(inputOperation) == "Balance inquiry") {
			inputOperation = 0;
			keyMap = new HashMap<>();
			keyMap.put(1, "ca");
			keyMap.put(2, "sa");
			keyMap.put(3, "mma");

			System.out.println("Balance of which account would you like to inquire?");

			for (Map.Entry<Integer, String> entry : keyMap.entrySet()) {
				System.out.println(entry.getKey() + ". " + entry.getValue());
			}

			inputOperation = getUserInput("Type a number: ");

			while (inputOperation != 1 && inputOperation != 2 && inputOperation != 3) {
				System.out.println("Incorrect input. Please type '1' or '2' or '3'.");
				inputOperation = getUserInput("Type a number: ");
			}

			ct = new CheckTransaction();
			System.out.println("Balance: " + ct.checkBalance(inputOperation));
			transaction = ct;
		}

		// 2.b. Withdraw
		else if (keyMap.get(inputOperation) == "Withdraw") {
			inputOperation = 0;
			keyMap = new HashMap<>();
			keyMap.put(1, "ca");
			keyMap.put(2, "sa");
			keyMap.put(3, "mma");

			System.out.println("Withdraw from which account?");

			for (Map.Entry<Integer, String> entry : keyMap.entrySet()) {
				System.out.println(entry.getKey() + ". " + entry.getValue());
			}

			inputOperation = getUserInput("Type a number: ");

			while (inputOperation != 1 && inputOperation != 2 && inputOperation != 3) {
				System.out.println("Incorrect input. Please type '1' or '2' or '3'.");
				inputOperation = getUserInput("Type a number: ");
			}

			System.out.println("How much would you like to withdraw?");

			int inputAmount = getUserInput("Type a number: ");

			while (inputAmount > ct.checkBalance(inputOperation)) {
				inputAmount = getUserInput("Amount exceed the balance. Try again: ");
			}

			ut.withdraw(inputOperation, inputAmount);
			transaction = ut;
		}

		// 2.c. Deposit
		else if (keyMap.get(inputOperation) == "Deposit") {
			inputOperation = 0;
			keyMap = new HashMap<>();
			keyMap.put(1, "ca");
			keyMap.put(2, "sa");
			keyMap.put(3, "mma");

			System.out.println("Deposit to which account?");

			for (Map.Entry<Integer, String> entry : keyMap.entrySet()) {
				System.out.println(entry.getKey() + ". " + entry.getValue());
			}

			inputOperation = getUserInput("Type a number: ");

			while (inputOperation != 1 && inputOperation != 2 && inputOperation != 3) {
				System.out.println("Incorrect input. Please type '1' or '2' or '3'.");
				inputOperation = getUserInput("Type a number: ");
			}

			System.out.println("How much would you like to deposit?");

			int inputAmount = getUserInput("Type a number: ");

			ut.deposit(inputOperation, inputAmount);
			transaction = ut;
		}

		// 2.d. Calculate Interest
		else if (keyMap.get(inputOperation) == "Calculate interest") {

			inputOperation = 0;
			keyMap = new HashMap<>();
			keyMap.put(1, "Savings account");
			keyMap.put(2, "Money market account");

			System.out.println("Interest of which account would you like to calculate?");

			for (Map.Entry<Integer, String> entry : keyMap.entrySet()) {
				System.out.println(entry.getKey() + ". " + entry.getValue());
			}

			inputOperation = getUserInput("Type a number: ");

			while (inputOperation != 1 && inputOperation != 2) {
				System.out.println("Incorrect input. Please type '1' or '2'.");
				inputOperation = getUserInput("Type a number: ");
			}

			int accInd = inputOperation + 1;
			String accType = Account.getAccType(accInd);

			inputOperation = 0;
			keyMap = new HashMap<>();
			keyMap.put(1, "1%");
			keyMap.put(2, "2%");
			keyMap.put(3, "5%");
			keyMap.put(4, "10%");

			System.out.println("Which annual interest rate would you like to use?");

			for (Map.Entry<Integer, String> entry : keyMap.entrySet()) {
				System.out.println(entry.getKey() + ". " + entry.getValue());
			}

			inputOperation = getUserInput("Type a number: ");

			while (inputOperation != 1 && inputOperation != 2 && inputOperation != 3 && inputOperation != 4) {
				System.out.println("Incorrect input. Please type '1' or '2' or '3' or '4'.");
				inputOperation = getUserInput("Type a number: ");
			}

			float rate = (float) inputOperation / 100;
			float interest = ct.getInterest(accInd, rate);

			System.out.println(
					"Total balance with interest for your " + accType + ": " + String.format("%.2f", interest) + ".");
			transaction = ct;
		}

		String transactionString = "Request: " + transaction.getName() + " | " + " Amount: " + transaction.getAmount()
				+ " | " + " Succeded: " + transaction.isSucceeded();

		// 3. Receipt
		inputOperation = 0;
		keyMap = new HashMap<>();
		keyMap.put(1, "yes");
		keyMap.put(2, "no");

		System.out.println("Would you like to print a receipt?");

		for (Map.Entry<Integer, String> entry : keyMap.entrySet()) {
			System.out.println(entry.getKey() + ". " + entry.getValue());
		}

		inputOperation = getUserInput("Type a number: ");

		while (inputOperation != 1 && inputOperation != 2) {
			System.out.println("Incorrect input. Please type '1' or '2'.");
			inputOperation = getUserInput("Type a number: ");
		}

		if (keyMap.get(inputOperation) == "yes") {
			System.out.println(getReceipt(card, transactionString));
		}

		logger.info(transactionString);

		reader.close();
	}

	private static int getUserInput(String message) {
		System.out.println(message);

		try {
			return reader.nextInt();
		} catch (InputMismatchException e) {
			reader.next();
			System.out.println("Something went wrong, the string could not be converted to int.");
			return 0;
		}
	}

	/*
	 * 
	 * Receipt Methods
	 * 
	 */

	private static String getReceipt(Card card, String transactionString) throws ClassNotFoundException, SQLException {
		return (NAME + "\n" + ADDRESS + "\n" + WEB_ADDRESS + "\n" + "\n" + card.getBankName() + "\n"
				+ hideCardNum(Card.getNum()) + "\n" + transactionString);
	}

	private static String hideCardNum(BigInteger cardNum) {
		String stringCardNum = cardNum.toString();
		String hiddenCardNum = "";

		for (int c = 0; c < stringCardNum.length() - 4; c++) {
			hiddenCardNum = hiddenCardNum.concat("*");
		}

		for (int c = stringCardNum.length() - 4; c < stringCardNum.length(); c++) {
			hiddenCardNum = hiddenCardNum.concat(String.valueOf(stringCardNum.charAt(c)));
		}

		return hiddenCardNum;
	}

	public static int getAllowedPinInputCount() {
		return ALLOWED_PIN_INPUT_COUNT;
	}

}
