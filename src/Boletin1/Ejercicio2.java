package Boletin1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio2 {

	public static void main(String[] args) {
		
		
		String url = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
		String usuario = "root";
		String password = "57055705";

		try (Connection con = DriverManager.getConnection(url, usuario, password);
				PreparedStatement consulta = con
						.prepareStatement("Select productName, MSRP from products where MSRP < 100")) {
			ResultSet resultados = consulta.executeQuery();

			while (resultados.next()) {
				System.out.println(resultados.getString("productName") + " " + resultados.getDouble(2));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}
}
