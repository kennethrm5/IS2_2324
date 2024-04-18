package es.unican.is2.GestionVentasTienda;
public class VendedorEnPlantilla extends Vendedor {
	
	private TipoVendedor tipo;
	private String dni;
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	public VendedorEnPlantilla(String nombre, String id, String dni, TipoVendedor tipo) { // WMC +1
		super(nombre, id);
		this.tipo = tipo;
		this.dni=dni;
	}
	
	public TipoVendedor tipo() { // WMC +1
		return tipo;
	}
	
	public String dni() { // WMC +1
		return dni;
	}
	
	@Override
	public boolean equals(Object obj) { // WMC +1
		if (!(obj instanceof VendedorEnPlantilla))  // WMC +1 CCog +1
			return false;
		VendedorEnPlantilla v = (VendedorEnPlantilla) obj;
		return (v.getId().equals(getId()) && v.dni().equals(dni()));
	}
	
	/**
	 * Anhade una nueva venta al vendedor
	 * @param importe de la venta
	 */
	@Override
	public void anhade(double importe)  { // WMC +1
		
		
		this.setTotalVentas(this.getTotalVentas() + importe);
		
		double porcentaje = 0;
		
		if (this.tipo == TipoVendedor.Junior) {
			porcentaje = 0.005;
		} else if (this.tipo == TipoVendedor.Senior) {
			porcentaje = 0.01;
		}
		
		this.setComision(this.getComision() + (importe * porcentaje));
	}
	
}
