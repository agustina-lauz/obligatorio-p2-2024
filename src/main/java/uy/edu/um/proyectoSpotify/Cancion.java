package uy.edu.um.proyectoSpotify;
import lombok.*;

@Data
@Builder
@Setter
@Getter
public class Cancion {
    private String id;
    private String nombre;
    private String artista;
    private int ranking;
    private int movRankingDaily;
    private int movRankingWeekly;
    private String pais;
    private String fecha;
    private double tempo;


    @Override
    public String toString() {
        return "Cancion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", artista='" + artista + '\'' +
                ", ranking=" + ranking +
                ", movRankingDaily=" + movRankingDaily +
                ", movRankingWeekly=" + movRankingWeekly +
                ", ciudad='" + pais + '\'' +
                ", fecha='" + fecha + '\'' +
                ", tempo=" + tempo +
                '}';
    }
}
