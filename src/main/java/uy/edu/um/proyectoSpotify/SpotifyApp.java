package uy.edu.um.proyectoSpotify;

import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class SpotifyApp {


    public static void main(String[] args) {
        SpotifyApp app = new SpotifyApp();
        LectorCSV lector = new LectorCSV(',', '"');

        try {
            List<String[]> lectura = lector.leerCSVComplejo("universal_top_spotify_songs.csv");

            // Imprimir solo el primer registro
            if (!lectura.isEmpty()) {
                String[] primerRegistro = lectura.get(1);
                System.out.println(Arrays.toString(primerRegistro));
            } else {
                System.out.println("No se encontraron registros en el archivo CSV.");
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}