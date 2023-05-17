package Boletin1;

/*
Crea un programa que acepte un nombre de empleado y muestre todas las ventas
realizadas por este, en una línea que indique a qué cliente y el monto total de la
operación.
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Extras.DataBaseConnection;

public class Ejercicio6 {
    public static void main(String[] args) {

        // Conecta a la base de datos, declara la consulta de SQL y declara el Scanner.
		try (Connection con = DataBaseConnection.getInstance().getCon();
             // SQL que comprueba que existe un cliente con el nombre introducido por teclado:
             PreparedStatement comprobarNombre = con.prepareStatement("SELECT * from employees where" +
                     " firstName = ? AND lastName = ? ");
             /* SQL que muestra todas las ventas realizadas por este,
              en una línea que indique a qué cliente y el monto total de la operación:
              */
             PreparedStatement consulta = con.prepareStatement("SELECT o.orderNumber, c.customerName, " +
                     "SUM(od.quantityOrdered*od.priceEach) AS monto " +
                     "FROM orders o, customers c, orderdetails od, employees e " +
                     "WHERE o.customerNumber = c.customerNumber " +
                     "AND e.employeeNumber = c.salesRepEmployeeNumber " +
                     "and o.orderNumber = od.orderNumber " +
                     "AND e.firstName = ? " +
                     "AND e.lastName = ? " +
                     "GROUP BY o.orderNumber " +
                     "ORDER BY c.customerName;");
             Scanner sc = new Scanner(System.in);) {

            /* Declara por entrade de teclado los String correspondientes al nombre y apellido del cliente
            los manda con un setString a las consultas correspondientess gracias a los "?".
            */
            System.out.println("Introduzca el nombre del empleado");
            String nombreEmpleado = sc.nextLine();
            consulta.setString(1, nombreEmpleado);
            comprobarNombre.setString(1, nombreEmpleado);
            System.out.println("Introduzca el apellido1 del empleado");
            String ap1 = sc.nextLine();
            consulta.setString(2, ap1);
            comprobarNombre.setString(2, ap1);

            // Resultado de la consulta de comprobarNombre:
            ResultSet resultadoComprobarNombre = comprobarNombre.executeQuery();

            /*
            Si comprobar nombre da un resultado que devuelva que hay un nombre igual que el introducido por teclado
            en la base de datos, entonces ejecuta la siguiente consulta, si no, indica que no existe ningún cliente
            con ese nombre.
             */
            if (resultadoComprobarNombre.next()){

                // Resultado de la consulta general del ejercicio:
                ResultSet resultadoConsulta = consulta.executeQuery();
                while (resultadoConsulta.next()) {
                    // Imprime orderNumber, customerNumber y el monto.
                    // No es necesario escribir o.orderNumber.
                    System.out.printf("%8d | %-30s | %10.2f €\n",resultadoConsulta.getInt("orderNumber"),
                            resultadoConsulta.getString("customerName"),resultadoConsulta.getDouble("monto"));

                    /*
                    System.out.println(resultadoConsulta.getString("orderNumber") + " " +
                            resultadoConsulta.getString("customerName") + " " +
                            resultadoConsulta.getString("monto") + "€")
                     */
                }
            } else {
                System.out.println("No existe un cliente con ese nombre");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
