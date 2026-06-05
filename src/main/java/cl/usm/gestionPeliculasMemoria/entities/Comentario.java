package cl.usm.gestionPeliculasMemoria.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {
    private String usuario;
    private String comentario;
}
