package cl.usm.gestionPeliculasMemoria.repositories;

import cl.usm.gestionPeliculasMemoria.entities.Pelicula;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PeliculasRepositoryImpl implements PeliculasRepository {

    private final List<Pelicula> storage = new ArrayList<>();

    @Override
    public Pelicula insert(Pelicula pelicula) {
        if (pelicula.getId() == null) {
            throw new IllegalArgumentException("El ID de la pelicula no puede ser nulo");
        }
        for (Pelicula p : storage) {
            if (p.getId().equalsIgnoreCase(pelicula.getId())) {
                throw new IllegalArgumentException("La pelicula con ID " + pelicula.getId() + " ya existe");
            }
        }
        storage.add(pelicula);
        return pelicula;
    }

    @Override
    public List<Pelicula> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public Pelicula findById(String id) {
        if (id == null) {
            return null;
        }
        for (Pelicula p : storage) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }
}
