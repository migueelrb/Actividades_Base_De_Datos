package Mi_Entrada_y_salida;

public class PruebaExcepciones {

	public static void main(String[] args) {
		double d = MiEntradaSalida.leerDouble("Introduce un numero doble");
		System.out.println("El numero es: " + d);

		int d1 = MiEntradaSalida.leerEnteroPositivo("Introduce un entero positivo solamente");
		System.out.println("El numero es: " + d1);

		char d2 = MiEntradaSalida.leerCaracter("Introduce un caracter solamente");
		System.out.println("El caracter es: " + d2);

		int d5 = MiEntradaSalida.leerEntero("Introduce un numero: ");
		System.out.println("El numero es: " + d5);

		String[] opciones = { "Amarillo", "Rojo", "Azul", "Verde" };
		String color = MiEntradaSalida.devuelveOpcionElegida("Selecciona un color: ", opciones);
		System.out.println("Has elegido el color: " + color);

		char d3 = MiEntradaSalida.leerCaracterSN("Introduce Si o No solamente");
		System.out.println("Bien, has introducido = " + d3);

		String d4 = MiEntradaSalida.leerCadenaDeTexto("Introduce una cadena de texto");
		System.out.println("Bien, has introducido = " + d4);

		float d6 = MiEntradaSalida.leerFloat("Introduce un float");
		System.out.println("El numero es: " + d6);
	}

}
