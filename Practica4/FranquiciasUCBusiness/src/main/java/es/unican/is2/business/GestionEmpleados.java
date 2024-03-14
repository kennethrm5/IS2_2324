package es.unican.is2.business;

import java.util.HashMap;
import es.unican.is2.ccommon.*;
import es.unican.is2.dao.EmpleadosDAO;
import es.unican.is2.dao.TiendasDAO;

public class GestionEmpleados implements IGestionEmpleados {

	private TiendasDAO tiendas = null;
	private EmpleadosDAO empleados = null;
	private HashMap<String, Tienda> tiendaEmpleado = new HashMap<>();
	
	public GestionEmpleados(TiendasDAO tiendasDAO, EmpleadosDAO empleadosDAO) {
		
		tiendas = tiendasDAO;
		empleados = empleadosDAO;
	}

	@Override
	public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException {
		try {
			Tienda t = tiendas.tiendaPorNombre(nombre);
			if (t == null) {
				return null;
			}
			if (t.buscaEmpleado(e.getDNI()) != null) {
				throw new OperacionNoValidaException("El empleado ya existe.");
			}
			t.getEmpleados().add(e);
			empleados.crearEmpleado(e);
			tiendaEmpleado.put(e.getDNI(), t);
			return e;
		} catch (DataAccessException e1) {
			throw new DataAccessException();
		}		
	}

	@Override
	public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {

		Tienda t = tiendas.tiendaPorNombre(nombre);
		if (t == null) {
			throw new DataAccessException();
		}
		Empleado e = t.buscaEmpleado(dni);
		if (e == null) {
			throw new OperacionNoValidaException("El empleado no existe en esta tienda.");
		}
		t.getEmpleados().remove(e);
		empleados.eliminarEmpleado(e.getDNI());
		tiendaEmpleado.remove(e.getDNI());
		return e;

	}

	@Override
	public boolean trasladarEmpleado(String dni, String actual, String destino)
			throws OperacionNoValidaException, DataAccessException {

		Tienda t1 = tiendas.tiendaPorNombre(actual);
		Tienda t2 = tiendas.tiendaPorNombre(destino);

		if ( t1 == null || t2  == null) {
			throw new OperacionNoValidaException("Las tiendas no existen.");
		}
		Empleado e = t1.buscaEmpleado(dni);
		if ( !tiendaEmpleado.containsKey(dni)) {
			throw new DataAccessException();
		}

		if (e == null) {
			throw new DataAccessException();
		}

		t1.getEmpleados().remove(e);
		t1.getEmpleados().add(e);
		tiendaEmpleado.replace(e.getDNI(), t2);
		return true;
		
	}

	@Override
	public Empleado empleado(String dni) throws DataAccessException {
		if (!tiendaEmpleado.containsKey(dni)) {
			return null;
		}
		Empleado e = tiendaEmpleado.get(dni).buscaEmpleado(dni);
		if (e == null) {
			throw new DataAccessException();
		}
		return e;
	}
}
