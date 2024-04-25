package es.unican.is2.GestionVentasTiendaRefactor;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa una tienda con un conjunto de vendedores. Gestiona las
 * ventas realizadas y las comisiones asignadas a cada vendedor. Los datos de la
 * tienda se almacenan en un fichero de texto que se pasa como parametro al
 * crear la tienda
 */
public class Tienda {

	private List<Vendedor> lista = new LinkedList<Vendedor>();
	private List<Vendedor> senior = new LinkedList<Vendedor>();
	private List<Vendedor> junior = new LinkedList<Vendedor>();
	private List<Vendedor> practicas = new LinkedList<Vendedor>();
	private String direccion;
	private String nombre;

	private String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * @param datos Path absoluto del fichero de datos
	 */
	public Tienda(String datos) { // WMC +1
		this.datos = datos;
	}

	/**
	 * Retorna la direccion de la tienda
	 * @return Direccion de la tienda
	 */
	public String direccion() { // WMC +1
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	public String nombre() { // WMC +1
		return nombre;
	}

	/**
	 * Anhade un nuevo vendedor a la tienda
	 * @param nuevo El vendedor a anhadir
	 * @return true si el vendedor se ha anhadido 
	 *         false si ya existe el vendedor
	 */
	public boolean anhade(Vendedor nuevo) throws DataAccessException { // WMC +1 CBO +1
		Vendedor v = buscaVendedor(nuevo.getId());
		if (v != null) { // WMC +1 CCog +1
			return false;
		}
		this.lista.add(nuevo);
		
		if (nuevo instanceof vendedorEnPracticas) {
			this.practicas.add(nuevo);
		} else if (nuevo instanceof VendedorEnPlantilla) {
			VendedorEnPlantilla vp = (VendedorEnPlantilla) nuevo;
			if (vp.tipo().equals(TipoVendedor.Junior)) {
				this.junior.add(vp);
			} else {
				this.senior.add(vp);
			}
		}
		
		vuelcaDatos();
		return true;
	}

	/**
	 * Elimina el vendedor cuyo id se pasa como argumento
	 * @param id
	 * @return true si se elimina el vendedor false si no existe el vendedor
	 */
	public boolean eliminaVendedor(String id) throws DataAccessException { // WMC +1
		Vendedor v = buscaVendedor(id);
		if (v == null) {
			return false;
		}
		lista.remove(v);
		
		if (v instanceof vendedorEnPracticas) {
			this.practicas.remove(v);
		} else if (v instanceof VendedorEnPlantilla) {
			VendedorEnPlantilla vp = (VendedorEnPlantilla) v;
			if (vp.tipo().equals(TipoVendedor.Junior)) {
				this.junior.remove(vp);
			} else {
				this.senior.remove(vp);
			}
		}
		
		vuelcaDatos();
		return true;
	}

	/**
	 * Anhade una venta a un vendedor
	 * @param id      Id del vendedor
	 * @param importe Importe de la venta
	 * @return true si se anhade la venta false si no se encuentra el vendedor
	 */
	public boolean anhadeVenta(String id, double importe) throws DataAccessException { // WMC +1
		Vendedor v = buscaVendedor(id);
		if (v == null) { // WMC +1 CCog +1
			return false;
		}
		
		v.anhade(importe);
		
		vuelcaDatos();
		return true;
	}

	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese dni o null si no existe ninguno
	 */
	public Vendedor buscaVendedor(String id) throws DataAccessException {

		if (id == null) {
			throw new DataAccessException();
		}
		
		for (Vendedor v : lista) {
			if (v.getId().equals(id)) {
				return v;
			}
		}
		return null;
	}

	/**
	 * Retorna la lista de vendedores actuales de la tienda
	 * 
	 * @return La lista de vendedores
	 */
	public List<Vendedor> vendedores() throws DataAccessException {

		return this.lista;
	}

	/**
	 * Actualiza el fichero datosTienda.txt con los datos actualizados de
	 * los vendedores
	 */
	private void vuelcaDatos() throws DataAccessException {
		PrintWriter out = null;

		try {

			out = new PrintWriter(new FileWriter(datos));

			out.println(nombre);
			out.println(direccion);
			out.println();
			out.println("Senior");
			for (Vendedor v : senior) { // WMC +1 CCog +1
				VendedorEnPlantilla v1 = (VendedorEnPlantilla) v;
				out.println("  Nombre: " + v1.getNombre() + " Id: " + v1.getId() + " DNI: " + v1.dni()
						+ " TotalVentasMes: " + v1.getTotalVentas() + " TotalComision: "+ v1.getComision());
			}
			out.println();
			out.println("Junior");
			for (Vendedor v : junior) { // WMC +1 CCog +1
				VendedorEnPlantilla v2 = (VendedorEnPlantilla) v;
				out.println("  Nombre: " + v2.getNombre() + " Id: " + v2.getId() + " DNI: " + v2.dni()
						+ " TotalVentasMes: " + v2.getTotalVentas() + " TotalComision: "+ v2.getComision());
			}
			out.println();
			out.println("Practicas");
			for (Vendedor v : practicas) { // WMC +1 CCog +1
				vendedorEnPracticas v3 = (vendedorEnPracticas) v;
				out.println("  Nombre: " + v3.getNombre() + " Id: " + v3.getId() + " DNI: " + v3.getDni()
						+ " TotalVentasMes: " + v3.getTotalVentas());
			}
		} catch (IOException e) { // CCog +1
			throw new DataAccessException();

		} finally {
			if (out != null) // WMC +1 CCog +1
				out.close();
		}
	}

}
