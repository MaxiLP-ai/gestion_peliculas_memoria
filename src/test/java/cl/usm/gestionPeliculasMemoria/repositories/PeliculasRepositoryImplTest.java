package cl.usm.gestionPeliculasMemoria.repositories;

import cl.usm.gestionPeliculasMemoria.entities.Pelicula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PeliculasRepositoryImplTest {

    private PeliculasRepositoryImpl repo;

    @BeforeEach
    void setUp() {
        repo = new PeliculasRepositoryImpl();
    }

    @Test
    void testInsertOk() {
        Pelicula p = new Pelicula("av-202", "Avatar", "James Cameron", null, null);
        Pelicula res = repo.insert(p);

        assertNotNull(res);
        assertEquals("av-202", res.getId());
        
        List<Pelicula> lista = repo.findAll();
        assertEquals(1, lista.size());
    }

    @Test
    void testInsertDuplicadoLanzaExcepcion() {
        Pelicula p1 = new Pelicula("sh-302", "Shrek 2", "Andrew Adamson", null, null);
        Pelicula p2 = new Pelicula("SH-302", "Shrek 2 Reloaded", "Andrew Adamson", null, null);

        repo.insert(p1);

        assertThrows(IllegalArgumentException.class, () -> {
            repo.insert(p2);
        });
        System.out.println("DEBUG: excepcion duplicados controlada");
    }

    @Test
    void testFindAllVacio() {
        List<Pelicula> res = repo.findAll();
        assertTrue(res.isEmpty());
    }

    @Test
    void testFindByIdOk() {
        Pelicula p = new Pelicula("int-77", "Interstellar", "Christopher Nolan", null, null);
        repo.insert(p);

        Pelicula res = repo.findById("INT-77");

        assertNotNull(res);
        assertEquals("Interstellar", res.getTitulo());
    }

    @Test
    void testFindByIdNotFound() {
        Pelicula res = repo.findById("star-wars");
        assertNull(res);
    }
}
