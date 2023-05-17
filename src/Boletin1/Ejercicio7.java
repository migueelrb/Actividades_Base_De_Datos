package Boletin1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Extras.DataBaseConnection;

public class Ejercicio7 {

	private static final int CONTADOR_MAXIMO = 1000000;
	private static final int CONTADOR_MAXIMO2 = 2000000;

	public static void main(String[] args) throws RuntimeException {

		// Conecta a la base de datos, declara la consulta de SQL y declara el Scanner.
		try (Connection con = DataBaseConnection.getInstance().getCon();
				PreparedStatement consultaPreparada = con
						.prepareStatement("INSERT INTO productlines (productLine) values (?)");
				Statement consulta = con.createStatement()) {
			int contador = 0;
			long miliSegundos = System.currentTimeMillis();
			while (contador <= CONTADOR_MAXIMO) {
				String l = "INSERT INTO productlines (productLine) values (\"RANDOM_" + contador + "\");";
				consulta.executeUpdate(l);
				contador++;
			}

			long miliSegundos2 = System.currentTimeMillis();
			while (contador < CONTADOR_MAXIMO2) {
				consultaPreparada.setString(1, "RANDOM_" + contador);
				consultaPreparada.executeUpdate();
				contador++;
			}
			long miliSegundos3 = System.currentTimeMillis();
			
			System.out.println("La versión con Statement ha tardado un tiempo de : " + (miliSegundos2 - miliSegundos) + " ms");
			System.out.println("La versión con PreparedStatement ha tardado un tiempo de : " + (miliSegundos3 - miliSegundos2) + " ms");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}