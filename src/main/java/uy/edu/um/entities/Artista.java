package uy.edu.um.entities;
import lombok.*;


@Data
@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor


public class Artista {

    private String nombre;
    private int apariciones = 0;

    public Artista(String nombre) {
        this.nombre = nombre;
        this.apariciones = 0;  // Inicializa las apariciones a 0
    }
}