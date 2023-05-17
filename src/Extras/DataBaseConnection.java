package Extras;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection implements Closeable {

	private static DataBaseConnection dbc;

	String url = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
	String usuario = "root";
	String password = "57055705";

	private Connection con;

	private DataBaseConnection() throws SQLException {
		con = DriverManager.getConnection(url, usuario, password);

	}

	public static DataBaseConnection getInstance() throws SQLException {
		if (dbc == null) {
			dbc = new DataBaseConnection();
		}
		return dbc;

	}

	@Override
	public void close() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public Connection getCon() {
		return con;
	}

}
