package Boletin1;

import Extras.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Ejercicio10 {

    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        /* if (insertarProducto()) {
            System.out.println("El producto se ha insertado con exito");
        } else {
            System.out.println("Hubo un error al insertar el producto");
        } */

        realizarPedido();

    }

    public static boolean insertarProducto() {
        try (Connection con = DataBaseConnection.getInstance().getCon();
             PreparedStatement consultaPreparada = con
                     .prepareStatement("INSERT INTO products values (?,?,?,?,?,?,?,?,?)");
             PreparedStatement consultaCategorias = con.prepareStatement("SELECT productLine from productLines");
             PreparedStatement consultaCodigos = con
                     .prepareStatement("SELECT productCode from products where productCode = ?");
             Statement consulta = con.createStatement()) {

            // Petición del código del producto
            boolean salida = false;
            do {
                System.out.println("Inserte el codigo del producto: ");
                String codigoProducto = teclado.nextLine();
                consultaCodigos.setString(1, codigoProducto);
                ResultSet resultadoCodigos = consultaCodigos.executeQuery();
                if (!resultadoCodigos.next()) {
                    consultaPreparada.setString(1, codigoProducto);
                    salida = true;
                } else {
                    System.out.println("El código que intentas introducir ya existe en la base de datos");
                }
            } while (!salida);

            System.out.println("Inserte el nombre del producto: ");
            String nombreProducto = teclado.nextLine();
            consultaPreparada.setString(2, nombreProducto);

            ResultSet listadoCategorias = consultaCategorias.executeQuery();
            ArrayList<String> arrayCategorias = new ArrayList<>();
            while (listadoCategorias.next()) {
                arrayCategorias.add(listadoCategorias.getString(1));
            }
            String[] listaCategoriasArray = new String[arrayCategorias.size()];
            String categoria = UserDataCollector.getStringDeOpciones("Seleccione una de las categorias siguientes:",
                    arrayCategorias.toArray(listaCategoriasArray));
            consultaPreparada.setString(3, categoria);

            System.out.println("Inserte la escala del producto: ");
            String escalaProducto = teclado.nextLine();
            consultaPreparada.setString(4, escalaProducto);

            System.out.println("Inserte el vendedor del producto: ");
            String vendedorProducto = teclado.nextLine();
            consultaPreparada.setString(5, vendedorProducto);

            System.out.println("Inserte la descripcion del producto: ");
            String descripcionProducto = teclado.nextLine();
            consultaPreparada.setString(6, descripcionProducto);

            System.out.println("Inserte el stock del producto: ");
            int cantidadProducto = Integer.parseInt(teclado.nextLine());
            consultaPreparada.setInt(7, cantidadProducto);

            System.out.println("Inserte el precio del producto: ");
            float precioProducto = Float.parseFloat(teclado.nextLine());
            consultaPreparada.setFloat(8, precioProducto);

            System.out.println("Inserte el MSRP del producto:");
            float msrp = Float.parseFloat(teclado.nextLine());
            consultaPreparada.setFloat(9, msrp);

            if (consultaPreparada.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean realizarPedido() {
        try (Connection con = DataBaseConnection.getInstance().getCon()) {

            int numCliente = getNumeroCliente(con);
            String categoria = solicitarCategoria(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    /**
     * Este método devuelve el número del cliente que hace el pedido
     *
     * @param con
     * @return
     */
    public static int getNumeroCliente(Connection con) {

        try (Statement consultaClientes = con.createStatement();) {


            ResultSet resultadoClientes = consultaClientes
                    .executeQuery("SELECT customerNumber, customerName FROM customers");

            /* Un mapa relaciona dos valores a través de un apuntador */
            HashMap<Integer, String> estudiar = new HashMap<>();

            while (resultadoClientes.next()) {
                System.out.printf("%d : %s\n", resultadoClientes.getInt("customerNumber"),
                        resultadoClientes.getString("customerName"));
                estudiar.put(resultadoClientes.getInt("customerNumber"), resultadoClientes.getString("customerName"));

            }
            int numCliente = -1;
            do {
                System.out.println("Elija uno de los clientes");
                numCliente = Integer.parseInt(teclado.nextLine());
                // Mientras el array no contenga el cliente escrito por teclado
                if (!estudiar.containsKey(numCliente)) {
                    System.out.println("El cliente que ha escrito no esta en la base de datos");
                } else {
                    System.out.println("Se ha seleccionado el cliente");
                }

            } while (!estudiar.containsKey(numCliente));

            return numCliente;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static String solicitarCategoria(Connection con) {

        try (Statement consultaCategoria = con.createStatement();) {

            ResultSet listadoCategorias = consultaCategoria.executeQuery("SELECT productLine, textdescription FROM productLines");

            HashMap<String, String> categorias = new HashMap<>();

            while (listadoCategorias.next()) {
                System.out.printf("%s : %s\n", listadoCategorias.getString("productLine"),
                        listadoCategorias.getString("textdescription"));
                categorias.put(listadoCategorias.getString("productLine"),
                        listadoCategorias.getString("textdescription"));

            }

            String nombreCategoria;
            do {
                System.out.println("Elije la categoria:");
                nombreCategoria = (teclado.nextLine());
                // Mientras el array no contenga el cliente escrito por teclado
                if (!categorias.containsKey(nombreCategoria)) {
                    System.out.println("La categoria no existe en la base de datos");
                } else {
                    System.out.println("Se ha seleccionado la categoria: " + nombreCategoria);
                }

            } while (!categorias.containsKey(nombreCategoria));

            return nombreCategoria;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
