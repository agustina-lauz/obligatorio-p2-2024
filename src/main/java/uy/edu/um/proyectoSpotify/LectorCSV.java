package uy.edu.um.proyectoSpotify;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LectorCSV {

    private char separador;
    private char comillas;

    public LectorCSV(char separador, char comillas) {
        this.separador = separador;
        this.comillas = comillas;
    }

    public List<String[]> leerCSVComplejo(String path) throws IOException, CsvValidationException {
        CSVReader lector = new CSVReader(new FileReader(path));
        String[] linea;
        List<String[]> lineasLeidas = new ArrayList<>();

        while ((linea = lector.readNext()) != null) {
            lineasLeidas.add(linea);
        }

        if (lector != null) {
            lector.close();
        }

        return lineasLeidas;
    }
}