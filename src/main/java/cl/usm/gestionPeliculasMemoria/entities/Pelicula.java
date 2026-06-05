package cl.usm.gestionPeliculasMemoria.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula implements Serializable {

    @NotBlank(message = "Debe ingresar un ID para la pelicula")
    private String id;

    @NotBlank(message = "Debe ingresar un titulo")
    private String titulo;

    @NotBlank(message = "Debe ingresar un director")
    private String director;

    private String tokenDescarga;

    private Comentario[] comentarios;
}
