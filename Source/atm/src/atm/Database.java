package atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public Database(String query, String colName) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/atm?" + "user=root&password=1234&useSSL=false");

		statement = connection.createStatement();
		resultSet = statement.executeQuery(query);
		
		while (resultSet.next()) {
			System.out.println("Data fetched from db: " + resultSet.getString(colName));
		}
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