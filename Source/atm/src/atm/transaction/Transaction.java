package atm.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Transaction {
	
	protected String name;
	protected float amount;
	protected boolean succeeded;
	
	protected Connection connection;
	protected Statement statement;
	protected Statement preparedStatement;
	protected ResultSet resultSet;
	
	public Transaction() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/atm?" + "user=root&password=1234&useSSL=false");
		statement = connection.createStatement();
		preparedStatement = connection.createStatement();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public boolean isSucceeded() {
		return succeeded;
	}

	public void setSucceeded(boolean succeeded) {
		this.succeeded = succeeded;
	}
	
	public Statement getStmt() {
		return this.statement;
	}

	public void setStatement(Statement stmt) {
		this.statement = stmt;
	}

	public ResultSet getResultSet() {
		return this.resultSet;
	}

	public void setResultSet(ResultSet rs) {
		this.resultSet = rs;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	
}
