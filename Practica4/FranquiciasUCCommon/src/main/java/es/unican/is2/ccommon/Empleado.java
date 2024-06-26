package es.unican.is2.ccommon;


import java.time.LocalDate;
/**
 * Clase que representa un empleado de la franquicia, 
 * con sus datos personales 
 * y su estado en la franquicia (baja y categoria)
 */
public class Empleado {
	
	private String DNI;
	private String nombre;
	private Categoria categoria;
	private LocalDate fechaContratacion;
	private boolean baja = false;
	
	public Empleado() {	}
	
	/**
	 * Constructor del empleado con DNI, nombre, categoria y fecha de contratacion.
	 * Por defecto, baja se inicializa a false. 
	 * @param DNI
	 * @param nombre
	 * @param categoria
	 * @param fechaContratacion
	 * @throws OperacionNoValidaException 
	 */
	public Empleado(String DNI, String nombre, Categoria categoria, LocalDate fechaContratacion) throws OperacionNoValidaException {
		if (DNI == null || DNI.isEmpty()) {
			throw new OperacionNoValidaException("DNI no puede ser nulo o vacio");
		}

		if (nombre == null || nombre.isEmpty()) {
			throw new OperacionNoValidaException("Nombre no puede ser nulo o vacio");
		}

		if (categoria == null) {
			throw new OperacionNoValidaException("Categoria no puede ser nulo");
		}

		if (fechaContratacion == null || fechaContratacion.isAfter(LocalDate.now())) {
			throw new OperacionNoValidaException("Fecha de contratacion no puede ser nula o posterior a la actual");
		}
		
		this.nombre = nombre;
		this.DNI=DNI;
		this.categoria=categoria;
		this.fechaContratacion=fechaContratacion;

	}
	
	/**
	 * Retorna el sueldo bruto del empleado
	 */
	public double sueldoBruto() {
		int fechaActual = LocalDate.now().getYear();
		int antiguedad = fechaActual - this.fechaContratacion.getYear();
		double sueldoBruto = 0;
		
		switch (this.categoria) { 
			case ENCARGADO:
		    	sueldoBruto = 2000;
		    	break;
		    case VENDEDOR:
		    	sueldoBruto = 1500;
		    	break;
		    case AUXILIAR:
		    	sueldoBruto = 1000;
		    	break;
		    default:
		    	sueldoBruto = 0;
		}
		
		if (antiguedad > 20) {
			sueldoBruto += 200;
		} else if (antiguedad > 10) {
			sueldoBruto += 100;
		} else if (antiguedad > 5) {
			sueldoBruto += 50;
		}
		
		if (this.baja) {
			sueldoBruto *= 0.75;
		}
		
		return sueldoBruto;
	}
	
	/** 
	 * Dar de baja al empleado
	 */
	public void darDeBaja() {
		this.baja=true;
	}
	
	/**
	 * Dar de alta al empleado
	 */
	public void darDeAlta() {
		this.baja=false;
	}
	
	
	/**
	 * Retorna el dni del vendedor
	 * @return id
	 * @throws OperacionNoValidaException 
	 */
	public String getDNI() {
		return DNI;
	}
	
	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Retorna la categoria del empleado
	 *  @return categoria
	 */
	public Categoria getCategoria () {
		return categoria;
	}
	
	/**
	 * Retorna la fecha de contrato
	 * @return Fecha de contratacion
	 */
	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}
	
	/**
	 * Retorna si el empleado est� de baja
	 * @return true si esta de baja
	 *         false si no lo esta
	 */
	public boolean getBaja() {
		return baja;
	}
		
	
	public void setDNI(String dNI) throws OperacionNoValidaException {
		if (dNI == null || dNI.isEmpty()) {
			throw new OperacionNoValidaException("Nombre no puede ser nulo o vacio");
		}
		DNI = dNI;
	}

	public void setNombre(String nombre) throws OperacionNoValidaException {
		if (nombre == null || nombre.isEmpty()) {
			throw new OperacionNoValidaException("Nombre no puede ser nulo o vacio");
		}
		this.nombre = nombre;
	}
	
	public void setFechaContratacion(LocalDate fechaContratacion) throws OperacionNoValidaException {
		if (fechaContratacion == null || fechaContratacion.isAfter(LocalDate.now())) {
			throw new OperacionNoValidaException("Fecha de contratacion no puede ser nula o posterior a la actual");
		}
		this.fechaContratacion = fechaContratacion;
	}
	
	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public void setCategoria(Categoria categoria) throws OperacionNoValidaException {
		if (categoria == null) {
			throw new OperacionNoValidaException("Categoria no puede ser nulo");
		}
		this.categoria = categoria;
	}
	
}
