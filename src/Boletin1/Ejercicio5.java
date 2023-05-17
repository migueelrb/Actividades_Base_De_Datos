package Boletin1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import Extras.DataBaseConnection;

public class Ejercicio5 {
	public static void main(String[] args) {

		// Conecta a la base de dato, declara la ocnsulta de SQL y declara el Scanner
		try (Connection con = DataBaseConnection.getInstance().getCon();
				PreparedStatement consulta = con
						.prepareStatement("select productVendor as proovedor from products where productName = ?");
				Scanner sc = new Scanner(System.in);) {

			System.out.println("Introduzca el nombre del producto del que desas saber el proovedor");
			String nombreProducto = sc.nextLine();
			consulta.setString(1, nombreProducto);
			ResultSet result = consulta.executeQuery();

			while (result.next()) {
				System.out.println(result.getString("proovedor"));
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());

		}

	}
}