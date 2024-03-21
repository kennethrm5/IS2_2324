package es.unican.is2.ccommon;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EmpleadoTest {

	@Test
	public void testConstructor() {
		
		// Casos de prueba válidos
		Empleado sut = new Empleado("abuga", "nano", Categoria.ENCARGADO, LocalDate.now());
		
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
		
		// Casos de prueba no válidos
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
		
		
	}
	
	@Test
	public void testSueldo() {
		// Casos de prueba válidos
		Empleado sut = new Empleado("abuga", "nano", Categoria.ENCARGADO, LocalDate.of(2024, 3, 14));
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
	}
}
