package Mi_Entrada_y_salida;

import java.util.Scanner;

public class MiEntradaSalida {

	private static Scanner teclado = new Scanner(System.in);

	public static double leerDouble(String mensaje) {

		double res = 0;
		boolean ok = false;

		do {
			System.out.println(mensaje);
			try {
				res = Double.parseDouble(teclado.nextLine());
				ok = true;
			} catch (NumberFormatException e) {
				System.out.println("Tienes que introducir un numero");
			}
		} while (!ok);
		return res;

	}

	public static int leerEnteroDeRango(String mensaje, int numMinimo, int numMaximo) {
		int opcionElegida = 0;
		do {
			System.out.println(mensaje);
			opcionElegida = Integer.parseInt(teclado.nextLine());
			if (opcionElegida > numMaximo || opcionElegida < numMinimo) {
				System.out.println("Has introducido una opción fuera del rango");
			}
		} while (opcionElegida > numMaximo || opcionElegida < numMinimo);

		return opcionElegida;
	}

	public static int leerEntero(String mensaje) {
		int numero = 0;
		boolean cambio = true;
		while (cambio) {
			try {
				System.out.println(mensaje);
				numero = Integer.parseInt(teclado.nextLine());
				cambio = false;
			} catch (NumberFormatException e) {
				System.out.println("Tienes que introducir un numero");
			}
		}
		return numero;
	}

	public static int leerEnteroPositivo(String mensaje) {
		int numero = 0;
		do {
			System.out.println(mensaje);
			try {
				numero = Integer.parseInt(teclado.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Tienes que introducir un numero");
			}
		} while (numero < 1);

		return numero;

	}

	public static float leerFloat(String mensaje) {
		float numero = 0;
		boolean ok = false;

		do {
			System.out.println(mensaje);
			try {
				numero = Float.parseFloat(teclado.nextLine());
				ok = true;
			} catch (NumberFormatException e) {
				System.out.println("Tienes que introducir un numero");
			}
		} while (!ok);
		return numero;

	}

	public static char leerCaracter(String mensaje) { // Falta que vuelva a pedir si introduce un espacio
		char caracter = 0;
		do {
			System.out.println(mensaje);
			try {
				caracter = teclado.nextLine().charAt(0);
			} catch (StringIndexOutOfBoundsException e) {
				System.out.println("Tienes que introducir un caracter");
			}
		} while (caracter == 0);

		return caracter;

	}

	/**
	 * Este método lee un caracter que debe ser S o N y transforma dicha letra en
	 * mayúscula.
	 * 
	 * @param mensaje
	 * @return
	 */
	public static char leerCaracterSN(String mensaje) {
		char caracter = ' ';
		char letraMayus = ' ';
		do {
			System.out.println(mensaje);
			try {
				caracter = teclado.nextLine().charAt(0);
				letraMayus = Character.toUpperCase(caracter);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Tienes que introducir un caracter");
			}
		} while (letraMayus != 'S' && letraMayus != 'N');
		return letraMayus;

	}

	public static String leerCadenaDeTexto(String mensaje) {
		String texto = null;

		do {
			System.out.println(mensaje);
			texto = teclado.nextLine();
			if (texto.length() == 0) {
				System.out.println("Tienes que introducir algo");
			}
		} while (texto.length() == 0);

		return texto;

	}

	public static String devuelveOpcionElegida(String mensaje, String[] opciones) {
		int opcionElegida = 0;
		do {
			for (int i = 0; i < opciones.length; i++) {
				System.out.printf("%d = %s \n", i + 1, opciones[i]);
			}
			try {
				System.out.println(mensaje);
				opcionElegida = Integer.parseInt(teclado.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Debes introducir una opcion");
			}
		} while (opcionElegida <= 0 || opcionElegida > opciones.length);

		return opciones[opcionElegida - 1];

	}
}
