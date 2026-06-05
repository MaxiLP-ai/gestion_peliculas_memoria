package cl.usm.gestionPeliculasMemoria.controllers;

import cl.usm.gestionPeliculasMemoria.entities.Comentario;
import cl.usm.gestionPeliculasMemoria.entities.Pelicula;
import cl.usm.gestionPeliculasMemoria.services.PeliculasService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeliculasControllerTest {

    @Mock
    private PeliculasService peliculasService;

    @InjectMocks
    private PeliculasController peliculasController;

    @Test
    void testGetAllSinQuery() {
        Pelicula p1 = new Pelicula("int-77", "Interstellar", "Christopher Nolan", "tok_nolan99", null);
        Pelicula p2 = new Pelicula("chihiro-01", "El viaje de Chihiro", "Hayao Miyazaki", "chihiro_tok_8", null);
        List<Pelicula> lista = Arrays.asList(p1, p2);
        
        when(peliculasService.getAll()).thenReturn(lista);

        ResponseEntity<List<Pelicula>> res = peliculasController.getAll(null);

        assertNotNull(res);
        assertTrue(res.getStatusCode().is2xxSuccessful());
        assertEquals(2, res.getBody().size());
        System.out.println("DEBUG: testGetAllSinQuery - valor: " + res.getBody().size());
    }

    @Test
    void testGetAllConQuery() {
        Pelicula p = new Pelicula("chihiro-01", "El viaje de Chihiro", "Hayao Miyazaki", "chihiro_tok_8", null);
        List<Pelicula> lista = Arrays.asList(p);
        
        when(peliculasService.filter("chihiro")).thenReturn(lista);

        ResponseEntity<List<Pelicula>> res = peliculasController.getAll("chihiro");

        assertNotNull(res);
        assertTrue(res.getStatusCode().equals(HttpStatus.OK));
        assertEquals("El viaje de Chihiro", res.getBody().get(0).getTitulo());
    }

    @Test
    void testCrearPeliOk() {
        Pelicula p1 = new Pelicula("totoro-02", "Mi Vecino Totoro", "Hayao Miyazaki", null, null);
        Pelicula p2 = new Pelicula("totoro-02", "Mi Vecino Totoro", "Hayao Miyazaki", "totoro_tok_7", null);
        
        when(peliculasService.createPelicula(p1)).thenReturn(p2);

        ResponseEntity<?> res = peliculasController.createPelicula(p1);

        assertTrue(res.getStatusCode().is2xxSuccessful());
        Pelicula body = (Pelicula) res.getBody();
        assertNotNull(body);
        assertTrue("totoro_tok_7".equals(body.getTokenDescarga()));
    }

    @Test
    void testFindByIdOk() {
        Pelicula p = new Pelicula("int-77", "Interstellar", "Christopher Nolan", "tok_nolan99", null);
        when(peliculasService.findById("int-77")).thenReturn(p);

        ResponseEntity<Pelicula> res = peliculasController.findById("int-77");

        assertNotNull(res);
        assertTrue(res.getStatusCode() == HttpStatus.OK);
        assertEquals("Christopher Nolan", res.getBody().getDirector());
    }

    @Test
    void testFindByIdNotFound() {
        when(peliculasService.findById("inexistente")).thenReturn(null);

        ResponseEntity<Pelicula> res = peliculasController.findById("inexistente");

        assertNotNull(res);
        assertEquals(404, res.getStatusCode().value());
        System.out.println("DEBUG: testFindByIdNotFound - valor: " + res.getStatusCode().value());
    }

    @Test
    void testGetComentariosOk() {
        Comentario c1 = new Comentario("jperez", "La mejor de Nolan");
        Comentario c2 = new Comentario("amartinez", "Demasiado larga pero buena");
        Comentario[] comentarios = {c1, c2};
        Pelicula p = new Pelicula("int-77", "Interstellar", "Christopher Nolan", "tok_nolan99", comentarios);
        
        when(peliculasService.findById("int-77")).thenReturn(p);

        ResponseEntity<?> res = peliculasController.getComentarios("int-77");

        assertTrue(res.getStatusCode().is2xxSuccessful());
        Comentario[] body = (Comentario[]) res.getBody();
        assertEquals(2, body.length);
        assertEquals("jperez", body[0].getUsuario());
    }
}
