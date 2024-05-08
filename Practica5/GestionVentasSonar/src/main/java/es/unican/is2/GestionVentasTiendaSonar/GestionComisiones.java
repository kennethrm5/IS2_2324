package es.unican.is2.GestionVentasTiendaSonar;

//import java.util.LinkedList;
import java.util.List;

import fundamentos.Menu;
import fundamentos.Lectura;
import fundamentos.Mensaje;

/**
 * Gestion de las comisiones de vendedores de una tienda
 */
public class GestionComisiones {
	private static final int NUEVA_VENTA = 0;
    private static final int VENDEDOR_DEL_MES = 1;
    private static final int VENDEDORES = 2;
	/**
	 * Programa principal basado en menu
	 */
    public static void main(String[] args) {
        GestionComisiones gestionComisiones = new GestionComisiones();
        gestionComisiones.iniciar();
    }

    // Método para iniciar el programa y mostrar el menú
    private void iniciar() {
        Tienda tienda = new Tienda("C:\\temp\\datosTienda.txt");
        Menu menu = crearMenu();
        
        while (true) {
            int opcion = menu.leeOpcion();
            ejecutarOpcion(opcion, tienda);
        }
    }

    // Método para crear el menú
    private Menu crearMenu() {
        Menu menu = new Menu("Comisiones tienda");
        menu.insertaOpcion("Añadir venta", NUEVA_VENTA);
        menu.insertaOpcion("Vendedor del mes", VENDEDOR_DEL_MES);
        menu.insertaOpcion("Vendedores por ventas", VENDEDORES);
        return menu;
    }

    // Método para ejecutar la opción seleccionada por el usuario
    private void ejecutarOpcion(int opcion, Tienda tienda) {
        switch (opcion) {
            case NUEVA_VENTA:
                anhadirVenta(tienda);
                break;
            case VENDEDOR_DEL_MES:
                mostrarVendedorDelMes(tienda);
                break;
            case VENDEDORES:
                mostrarVendedores(tienda);
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    // Método para añadir una venta
    private void anhadirVenta(Tienda tienda) {
        Lectura lect = new Lectura("Datos Venta");
        lect.creaEntrada("ID Vendedor", "");
        lect.creaEntrada("Importe", "");
        lect.esperaYCierra();
        String dni = lect.leeString("ID Vendedor");
        double importe = lect.leeDouble("Importe");
        try {
            if (!tienda.anhadeVenta(dni, importe)) {
                mensaje("ERROR", "El vendedor no existe");
            }
        } catch (DataAccessException e) {
            mensaje("ERROR", "No se pudo guardar el cambio");
        }
    }

    // Método para mostrar al vendedor del mes
    private void mostrarVendedorDelMes(Tienda tienda) {
        try {
            String nombreVendedor = tienda.vendedorDelMes().getNombre();
            mensaje("VENDEDOR DEL MES", nombreVendedor);
        } catch (DataAccessException e) {
            mensaje("ERROR", "No se pudo acceder a los datos");
        }
    }

    // Método para mostrar los vendedores ordenados por ventas
    private void mostrarVendedores(Tienda tienda) {
        try {
            List<Vendedor> vendedores = tienda.vendedores();
            StringBuilder mensajeVendedores = new StringBuilder();
            for (Vendedor vendedor : vendedores) {
                mensajeVendedores.append(vendedor.getNombre())
                        .append(" (").append(vendedor.getId()).append(") ")
                        .append(vendedor.getTotalVentas()).append("\n");
            }
            mensaje("VENDEDORES", mensajeVendedores.toString());
        } catch (DataAccessException e) {
            mensaje("ERROR", "No se pudo acceder a los datos");
        }
    }

    /**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo Titulo de la ventana
	 * @param txt    Texto contenido en la ventana
	 */
    private static void mensaje(String titulo, String txt) { // WMC +1
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);
	}
}
