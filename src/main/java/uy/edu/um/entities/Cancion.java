package uy.edu.um.entities;
import lombok.*;

@Data
@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Cancion {
    private String id;
    private String nombre;
    private String artista;
    private int dailyRank;
    private String pais;
    private String fecha;
    private double tempo;

}


