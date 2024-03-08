package es.unican.is2.business;

import java.util.HashMap;
import es.unican.is2.ccommon.*;
import es.unican.is2.dao.TiendasDAO;

public class GestionEmpleados implements IGestionEmpleados {

	private TiendasDAO tiendas = new TiendasDAO();
	private HashMap<String, Tienda> empleados = new HashMap<>();
	
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
			empleados.put(e.getDNI(), t);
			return e;
		} catch (DataAccessException e1) {
			throw new DataAccessException();
		}		
	}

	@Override
	public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {
		try {
			Tienda t = tiendas.tiendaPorNombre(nombre);
			if (t == null) {
				return null;
			}
			Empleado e = t.buscaEmpleado(dni);
			if (e == null) {
				throw new OperacionNoValidaException("El empleado no existe en esta tienda.");
			}
			t.getEmpleados().remove(e);
			empleados.remove(e.getDNI());
			return e;
		} catch (DataAccessException e1) {
			throw new DataAccessException();
		}
	}

	@Override
	public boolean trasladarEmpleado(String dni, String actual, String destino)
			throws OperacionNoValidaException, DataAccessException {
		try {
			Tienda t1 = tiendas.tiendaPorNombre(actual);
			Tienda t2 = tiendas.tiendaPorNombre(destino);
			if (!empleados.containsKey(dni) || t1 == null || t2  == null) {
				return false;
			}
			Empleado e = t1.buscaEmpleado(dni);
			if (!empleados.containsKey(dni)) {
				throw new OperacionNoValidaException("El empleado ya existe.");
			}
			t1.getEmpleados().remove(e);
			t1.getEmpleados().add(e);
			empleados.replace(e.getDNI(), t2);
			return true;
		} catch (DataAccessException e1) {
			throw new DataAccessException();
		}
	}

	@Override
	public Empleado empleado(String dni) throws DataAccessException {
		if (!empleados.containsKey(dni)) {
			return null;
		}
		Empleado e = empleados.get(dni).buscaEmpleado(dni);
		if (e == null) {
			throw new DataAccessException();
		}
		return e;
	}
}
