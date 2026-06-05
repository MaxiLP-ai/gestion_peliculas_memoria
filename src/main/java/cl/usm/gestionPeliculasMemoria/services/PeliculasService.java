package cl.usm.gestionPeliculasMemoria.services;

import cl.usm.gestionPeliculasMemoria.entities.Pelicula;
import java.util.List;

public interface PeliculasService {
    Pelicula createPelicula(Pelicula pelicula);
    List<Pelicula> getAll();
    Pelicula findById(String id);
    List<Pelicula> filter(String query);
}
