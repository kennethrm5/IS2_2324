package es.unican.is2.ListaOrdenadaAcotada;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import es.unican.is2.listaOrdenadaAcotada.ListaOrdenadaAcotada;


public class ListaOrdenadaAcotadaTest {
	private ListaOrdenadaAcotada<Integer> lista;
	
	
	@BeforeEach
	public void startUp() {
		lista = new ListaOrdenadaAcotada<Integer>(10);
		lista.add(1);
		lista.add(2);
		lista.add(3);
	}
	
	@Test
	public void testGet() {
		assertEquals(1,lista.get(0));
		assertEquals(2,lista.get(1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-3));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(13));
		
	}
	
	@Test
	public void testAdd() {
		lista.add(4);
		assertEquals(4,lista.get(4));
		
		assertThrows(NullPointerException.class, () -> lista.add(null));
		
		for (int i = 5; i <= 10; i++) {
			lista.add(i);
		}
		
		assertThrows(IllegalStateException.class, () -> lista.add(11));
		
	}
	
	@Test
	public void testRemove() {
		lista.add(4);
		assertEquals(4,lista.remove(3));
		assertEquals(3,lista.remove(2));
		
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-2));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(15));
	}
	
	
	@Test
	public void testSize() {
		assertEquals(3,lista.size());
	}
	
	@Test
	public void testClear() {
		lista.clear();
		assertEquals(0,lista.size());
	}
	
}
