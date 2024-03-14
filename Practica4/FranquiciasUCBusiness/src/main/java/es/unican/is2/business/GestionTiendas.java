package es.unican.is2.business;

import java.util.HashMap;
import es.unican.is2.ccommon.*;
import es.unican.is2.dao.TiendasDAO;

public class GestionTiendas implements IGestionTiendas {

	private HashMap<String, Tienda> tiendas = new HashMap<>();
	private TiendasDAO tiendasDAO = null;
	
	public GestionTiendas(TiendasDAO tiendasDAO) {
		this.tiendasDAO = tiendasDAO;
	}

	@Override
	public Tienda nuevaTienda(Tienda t) throws DataAccessException {
		if (t != null) {
			tiendas.put(t.getNombre(), t);
			tiendasDAO.crearTienda(t);
		} else {
			throw new DataAccessException();
		}
		
		return t;
	}

	@Override
	public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {
		
		if (nombre == null) {
			throw new DataAccessException();
		}
		
		if (!tiendas.containsKey(nombre)) {
			throw new OperacionNoValidaException("Tienda no existe");
		}		
			
		Tienda n = tiendas.get(nombre);
			
		tiendas.remove(nombre);
		tiendasDAO.eliminarTienda(n.getId());
				
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
