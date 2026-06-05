package cl.usm.gestionPeliculasMemoria.services;

import cl.usm.gestionPeliculasMemoria.entities.Pelicula;
import cl.usm.gestionPeliculasMemoria.repositories.PeliculasRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeliculasServiceImplTest {

    @Mock
    private PeliculasRepository peliculasRepository;

    @InjectMocks
    private PeliculasServiceImpl peliculasService;

    @Test
    void testCrearPeliculaOk() {
        Pelicula p = new Pelicula();
        p.setId("int-77");
        p.setTitulo("Interstellar");
        p.setDirector("Christopher Nolan");

        when(peliculasRepository.insert(any(Pelicula.class))).thenAnswer(invocation -> {
            Pelicula temp = invocation.getArgument(0);
            return temp;
        });

        Pelicula res = peliculasService.createPelicula(p);

        assertTrue(res != null);
        assertTrue("Interstellar".equals(res.getTitulo()));
        System.out.println("DEBUG: testCrearPeliculaOk - valor: " + res.getTokenDescarga());
    }

    @Test
    void testObtenerTodas() {
        Pelicula p1 = new Pelicula("m-101", "The Matrix", "Lana Wachowski", "matrix_tok_9", null);
        Pelicula p2 = new Pelicula("sh-302", "Shrek 2", "Andrew Adamson", "shrek_tok_2", null);
        List<Pelicula> aux = Arrays.asList(p1, p2);

        when(peliculasRepository.findAll()).thenReturn(aux);

        List<Pelicula> res = peliculasService.getAll();

        assertEquals(2, res.size());
        assertTrue("The Matrix".equals(res.get(0).getTitulo()));
        System.out.println("DEBUG: testObtenerTodas - valor: " + res.size());
    }

    @Test
    void testBuscarPorId() {
        Pelicula p = new Pelicula("av-202", "Avatar", "James Cameron", "avatar_tok_3", null);
        when(peliculasRepository.findById("av-202")).thenReturn(p);

        Pelicula res = peliculasService.findById("av-202");

        assertNotNull(res);
        assertEquals("James Cameron", res.getDirector());
    }

    @Test
    void testFiltrarPorId() {
        Pelicula p1 = new Pelicula("chihiro-01", "El viaje de Chihiro", "Hayao Miyazaki", "chihiro_tok_8", null);
        Pelicula p2 = new Pelicula("totoro-02", "Mi Vecino Totoro", "Hayao Miyazaki", "totoro_tok_7", null);
        when(peliculasRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Pelicula> res = peliculasService.filter("CHIHIRO");

        assertTrue(res.size() > 0);
        assertEquals("chihiro-01", res.get(0).getId());
    }
}
