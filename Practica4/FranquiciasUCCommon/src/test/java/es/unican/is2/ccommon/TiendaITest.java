package es.unican.is2.ccommon;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TiendaITest {

	private Tienda tienda;
	private Empleado empleado1, empleado2, empleado3, empleado4;
	
	@BeforeEach
	public void setUp() throws Exception {
		tienda = new Tienda("Apul", "Calle Castro");
	}
	
	@Test
	public void testConstructor() {
		// Casos de prueba válidos
		assertEquals("Apul", tienda.getNombre());
		assertEquals("Calle Castro", tienda.getDireccion());
		
		// Casos de prueba no válidos
		assertThrows(NullPointerException.class, () -> new Tienda (null, "Una Direccion"));
		assertThrows(IllegalArgumentException.class, () -> new Tienda ("", "Una Direccion"));
		assertThrows(NullPointerException.class, () -> new Tienda ("Hola", null));
		assertThrows(IllegalArgumentException.class, () -> new Tienda ("Hola", ""));
	}
	
	@Test
	public void testGastoMensualSueldos() throws OperacionNoValidaException {
		// Casos de prueba válidos
		empleado1 = new Empleado("72279705C","Kenneth",Categoria.ENCARGADO,LocalDate.of(2003, 02, 21));
		empleado2 = new Empleado("72279706C","Juan",Categoria.AUXILIAR,LocalDate.now());
		empleado3 = new Empleado("72279707C","Gonzalo",Categoria.VENDEDOR,LocalDate.of(2012, 01, 01));
		empleado4 = new Empleado("72256234A","David",Categoria.AUXILIAR,LocalDate.of(2016, 01, 01));
		
	    assertEquals(0, tienda.gastoMensualSueldos());
	    List<Empleado> empleados = tienda.getEmpleados();
	    empleados.add(empleado1);
	    assertEquals(2200, tienda.gastoMensualSueldos());
	    empleados.add(empleado2);
	    empleados.add(empleado3);
	    empleados.add(empleado4);
	    assertEquals(5850,tienda.gastoMensualSueldos());
	}
	
	@Test
	public void testBuscaEmpleado() throws OperacionNoValidaException {
		// Casos de prueba válidos
		empleado1 = new Empleado("72279705C","Kenneth",Categoria.ENCARGADO,LocalDate.of(2003, 02, 21));
		empleado2 = new Empleado("72279706C","Juan",Categoria.AUXILIAR,LocalDate.now());
		List<Empleado> empleados = tienda.getEmpleados();
		
		assertEquals(null, tienda.buscaEmpleado("72279705C"));
		empleados.add(empleado1);
		empleados.add(empleado2);
	    assertEquals(empleado1, tienda.buscaEmpleado("72279705C"));
	    assertEquals(null, tienda.buscaEmpleado("72279707C"));
	    
	    // Casos de prueba no válidos
 		assertThrows(NullPointerException.class, () -> tienda.buscaEmpleado(null));
 		assertThrows(IllegalArgumentException.class, () -> tienda.buscaEmpleado(""));
	}
}
