package uy.edu.um.proyectoSpotify;

import uy.edu.um.tads.list.MyLinkedListImpl;
import uy.edu.um.tads.list.MyList;

public class MyArtistImpl {
    public String nombreArtista;
    MyList<MySongImpl> canciones = new MyLinkedListImpl<>();
}
