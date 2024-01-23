package table_lister;

import java.sql.SQLException;

public class TableListenerApp {

	public static void main(String[] args) throws SQLException {

		new DciDbConnector().listTables();
	}

}
