package Boletin1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio1 {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
		String usuario = "root";
		String password = "57055705";

		try (Connection con = DriverManager.getConnection(url, usuario, password);
				PreparedStatement consulta = con
						.prepareStatement("Select productLine , textDescription from productLines")) {
			ResultSet resultados = consulta.executeQuery();

			while (resultados.next()) {
				System.out.println(resultados.getString("productLine") + " " + resultados.getString("textDescription"));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}
}
