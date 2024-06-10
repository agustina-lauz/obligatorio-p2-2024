package uy.edu.um.proyectoSpotify;

import lombok.*;
import uy.edu.um.tads.list.MyLinkedListImpl;
import uy.edu.um.tads.list.MyList;

@Data
@Builder
@Setter
@Getter

public class Artista {

    private String nombre;
    private MyList<Cancion> canciones = new MyLinkedListImpl<>();

}