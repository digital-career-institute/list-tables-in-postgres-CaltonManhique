package table_lister;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DciDbConnector {
	private final String url = "jdbc:postgresql://localhost/test";
	private final String user = "postgres";
	private final String password = "1234";

	private Connection myConn = null;
	private Statement myStmt = null;
	private ResultSet myRs = null;

	public Connection connect() throws SQLException {

		try {

			myConn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myConn;
	}

	public void listTables() throws SQLException {

		try {

			DatabaseMetaData dbMetadata = connect().getMetaData();

			myRs = dbMetadata.getTables(null, null, "%", new String[] { "TABLE" });

			while (myRs.next()) {
				System.out.println(myRs.getString("TABLE_NAME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

			if (myConn != null)
				myConn.close();

			if (myStmt != null)
				myStmt.close();

			if (myRs != null)
				myRs.close();
		}
	}
}
