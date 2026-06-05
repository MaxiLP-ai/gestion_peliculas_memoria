package cl.usm.gestionPeliculasMemoria.repositories;

import cl.usm.gestionPeliculasMemoria.entities.Pelicula;
import java.util.List;

public interface PeliculasRepository {
    Pelicula insert(Pelicula pelicula);
    List<Pelicula> findAll();
    Pelicula findById(String id);
}
