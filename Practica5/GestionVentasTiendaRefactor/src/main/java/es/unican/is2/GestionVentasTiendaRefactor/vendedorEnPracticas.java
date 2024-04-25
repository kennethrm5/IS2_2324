package es.unican.is2.GestionVentasTiendaRefactor;
public class vendedorEnPracticas extends Vendedor {
	
	private String dni;
	
	/**
	 * Retorna un nuevo vendedor en practicas
	 * @param nombre
	 * @param dni
	 */
	public vendedorEnPracticas(String nombre, String id, String dni) { // WMC +1
		super(nombre, id);
		this.dni= dni;
	}
	
	public String getDni() { // WMC +1
		return dni;
	}

	@Override
	public boolean equals(Object obj) { // WMC +1
		if (!(obj instanceof vendedorEnPracticas))  // WMC +1 CCog +1
			return false;
		vendedorEnPracticas v = (vendedorEnPracticas) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni()));
	}
	
	/**
	 * Anhade una nueva venta al vendedor
	 * @param importe de la venta
	 */
	@Override
	public void anhade(double importe)  { // WMC +1
		
		this.setTotalVentas(this.getTotalVentas() + importe); // CBO +1 (Vendedor)
		this.setComision(0);
	}
	
}
