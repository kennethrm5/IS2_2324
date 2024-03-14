package es.unican.is2.ccommon;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EmpleadoTest {

	@Test
	public void testConstructor() {
		Empleado sut = new Empleado("abuga", "nano", Categoria.ENCARGADO, LocalDate.now());
		
		assertEquals("abuga", sut.getDNI());
		assertEquals("nano", sut.getDNI());
		assertEquals(Categoria.ENCARGADO, sut.getDNI());
		assertEquals(LocalDate.now(), sut.getDNI());
		
		sut = new Empleado("abuga", "nano", Categoria.VENDEDOR, LocalDate.now());
		assertEquals("abuga", sut.getDNI());
		assertEquals("nano", sut.getDNI());
		assertEquals(Categoria.VENDEDOR, sut.getDNI());
		assertEquals(LocalDate.now(), sut.getDNI());
		
		sut = new Empleado("abuga", "nano", Categoria.AUXILIAR, LocalDate.now());
		assertEquals("abuga", sut.getDNI());
		assertEquals("nano", sut.getDNI());
		assertEquals(Categoria.AUXILIAR, sut.getDNI());
		assertEquals(LocalDate.now(), sut.getDNI());
		
		sut = new Empleado(null, "nano", Categoria.AUXILIAR, LocalDate.now());
		assertEquals(null, sut.getDNI());
	}
}
