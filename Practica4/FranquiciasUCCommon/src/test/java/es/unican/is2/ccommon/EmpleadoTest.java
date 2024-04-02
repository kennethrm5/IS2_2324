package es.unican.is2.ccommon;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.beans.Transient;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmpleadoTest {
	
	private Empleado sut;
	
	@BeforeEach
	public void setUp() throws OperacionNoValidaException {
		sut = new Empleado("abuga", "nano", Categoria.ENCARGADO, LocalDate.now());
	}
	
	@Test
	public void testConstructorYGetYSet() throws OperacionNoValidaException {
		
		// Casos de prueba válidos
		assertEquals("abuga", sut.getDNI());
		assertEquals("nano", sut.getNombre());
		assertEquals(Categoria.ENCARGADO, sut.getCategoria());
		assertEquals(LocalDate.now(), sut.getFechaContratacion());
		assertEquals(false, sut.getBaja());
		
		sut = new Empleado("abuga", "nano", Categoria.VENDEDOR, LocalDate.now());
		assertEquals("abuga", sut.getDNI());
		assertEquals("nano", sut.getNombre());
		assertEquals(Categoria.VENDEDOR, sut.getCategoria());
		assertEquals(LocalDate.now(), sut.getFechaContratacion());
		assertEquals(false, sut.getBaja());
		
		sut = new Empleado("abuga", "nano", Categoria.AUXILIAR, LocalDate.now());
		assertEquals("abuga", sut.getDNI());
		assertEquals("nano", sut.getNombre());
		assertEquals(Categoria.AUXILIAR, sut.getCategoria());
		assertEquals(LocalDate.now(), sut.getFechaContratacion());
		assertEquals(false, sut.getBaja());
		
		/*
		 * Casos de prueba no válidos INICIALES
		sut = new Empleado(null, "nano", Categoria.AUXILIAR, LocalDate.now());
		assertEquals(null, sut.getDNI());
		sut.setDNI("");
		assertEquals("", sut.getDNI());
		
		sut = new Empleado("abuga", null, Categoria.AUXILIAR, LocalDate.now());
		assertEquals(null, sut.getNombre());
		sut.setNombre("");
		assertEquals("", sut.getNombre());
		
		sut = new Empleado("abuga", "nano", null, LocalDate.now());
		assertEquals(null, sut.getCategoria());
		
		sut = new Empleado("abuga", "nano", Categoria.AUXILIAR, null);
		assertEquals(null, sut.getFechaContratacion());
		sut.setFechaContratacion(LocalDate.of(2024, 12, 12));
		assertEquals(LocalDate.of(2024, 12, 12), sut.getFechaContratacion());
		 * */
		
		// Casos de prueba no válidos CLASE CORREGIDA
        sut = new Empleado("abuga", "nano", Categoria.ENCARGADO, LocalDate.now());
		assertThrows(OperacionNoValidaException.class, ()-> new Empleado(null, "nano", Categoria.AUXILIAR, LocalDate.now()));
		assertThrows(OperacionNoValidaException.class, ()-> new Empleado("", "nano", Categoria.AUXILIAR, LocalDate.now()));
		assertThrows(OperacionNoValidaException.class, ()-> sut.setDNI(null));
		assertThrows(OperacionNoValidaException.class, ()-> sut.setDNI(""));
		
		
		assertThrows(OperacionNoValidaException.class, ()-> new Empleado("abuga", null, Categoria.AUXILIAR, LocalDate.now()));
		assertThrows(OperacionNoValidaException.class, ()-> new Empleado("abuga", "", Categoria.AUXILIAR, LocalDate.now()));
		assertThrows(OperacionNoValidaException.class, ()-> sut.setDNI(null));
		assertThrows(OperacionNoValidaException.class, ()-> sut.setNombre(""));
		
		assertThrows(OperacionNoValidaException.class, ()-> new Empleado("abuga", "nano", null, LocalDate.now()));
		assertThrows(OperacionNoValidaException.class, ()-> sut.setCategoria(null));

		
		assertThrows(OperacionNoValidaException.class, ()-> new Empleado("abuga", "nano", Categoria.AUXILIAR, null));
		assertThrows(OperacionNoValidaException.class, ()-> new Empleado("abuga", "nano", Categoria.AUXILIAR, LocalDate.of(2024, 12, 12)));
		assertThrows(OperacionNoValidaException.class, ()-> sut.setFechaContratacion(null));
		assertThrows(OperacionNoValidaException.class, ()-> sut.setFechaContratacion(LocalDate.of(2024, 12, 12)));
		
	}
	
	@Test
	public void testSueldoBruto() throws OperacionNoValidaException {
		// Casos de prueba válidos
		sut.setFechaContratacion(LocalDate.of(2024, 3, 14));
		sut.setBaja(true);
		assertEquals(1500, sut.sueldoBruto());
		
		sut.setBaja(false);
		sut.setCategoria(Categoria.VENDEDOR);
		sut.setFechaContratacion(LocalDate.of(2018, 1, 1));
		assertEquals(1550, sut.sueldoBruto());
		
		sut.setCategoria(Categoria.AUXILIAR);
		sut.setBaja(true);
		sut.setFechaContratacion(LocalDate.of(2010, 1, 1));
		assertEquals(825, sut.sueldoBruto());
		
		sut.setCategoria(Categoria.ENCARGADO);
		sut.setBaja(false);
		sut.setFechaContratacion(LocalDate.of(2000, 1, 1));
		assertEquals(2200, sut.sueldoBruto());

		// Casos de prueba no válidos
		// No podemos hacerlos porque necesitamos que el sut sea final, pero no podemos hacerlo porque sí necesitamos modificarlo
	}

	@Test
	public void testDarDeBaja() throws OperacionNoValidaException {
		// Casos de prueba válidos
		sut.setBaja(true);
		sut.darDeBaja();
		assertEquals(true, sut.getBaja());

		sut.setBaja(false);
		sut.darDeBaja();
		assertEquals(true, sut.getBaja());
	}

	@Test
	public void testDarDeAlta() throws OperacionNoValidaException {
		// Casos de prueba válidos
		sut.setBaja(true);
		sut.darDeAlta();
		assertEquals(false, sut.getBaja());

		sut.setBaja(false);
		sut.darDeAlta();
		assertEquals(false, sut.getBaja());
	}
}
