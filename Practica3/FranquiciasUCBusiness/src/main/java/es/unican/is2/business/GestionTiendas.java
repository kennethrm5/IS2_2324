package es.unican.is2.business;

import java.util.HashMap;
import es.unican.is2.ccommon.*;

public class GestionTiendas implements IGestionTiendas {

	public HashMap<String, Tienda> tiendas = new HashMap<>();
	
	@Override
	public Tienda nuevaTienda(Tienda t) throws DataAccessException {
		if (t != null) {
			tiendas.put(t.getNombre(), t);
		} else {
			throw new DataAccessException();
		}
		
		return t;
	}

	@Override
	public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {
		if (tiendas.containsKey(nombre)) {
			throw new OperacionNoValidaException("Tienda ya existe");
		}
		
		Tienda n = null;
		
		if (nombre != null) {
			
			n = tiendas.get(nombre);
			
			if (n == null) {
				throw new DataAccessException();
			}
		} else {
			throw new DataAccessException();
		}
		
		return n;
	}

	@Override
	public Tienda tienda(String nombre) throws DataAccessException {		
		Tienda n = null;
		
		if (nombre != null) {
			n = tiendas.get(nombre);
		} else {
			throw new DataAccessException();
		}
		
		return n;
	}

}
