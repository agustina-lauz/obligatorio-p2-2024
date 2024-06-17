package uy.edu.um.entities;


import uy.edu.um.entities.Artista;
import uy.edu.um.entities.Cancion;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.tads.heap.MyHeap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SpotifyApp {

    private static final String csvFile = "spotify_data.csv";

    public static Map<String, Cancion> getCancionesPais(String date, String country) {
        Map<String, Cancion> canciones = new HashMap<>();
        CSVFormat format = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreSurroundingSpaces() // Ignora espacios en blanco alrededor de los delimitadores
                .withEscape('\\') // Especifica un carácter de escape
                .withQuote('"') // Define el carácter de cita
                .withDelimiter(',');

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             CSVParser csvParser = new CSVParser(br, format)) {
            for (CSVRecord record : csvParser) {
                String fecha = record.get("snapshot_date");
                String recordCountry = record.get("country");
                if (fecha.equals(date) && recordCountry.equalsIgnoreCase(country.trim())) {
                    Cancion cancion = new Cancion(
                            record.get("spotify_id"),
                            record.get("name"),
                            record.get("artists"),
                            Integer.parseInt(record.get("daily_rank")),
                            record.get("country"),
                            record.get("snapshot_date"),
                            Double.parseDouble(record.get("tempo"))
                    );
                    canciones.put(cancion.getId(), cancion);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return canciones;
    }

    public static void top10CancionesPais(String date, String country) {
        // Obtener canciones filtradas por fecha y país
        Map<String, Cancion> cancionesMap = getCancionesPais(date, country);
        // Definir un comparador para el heap basado en daily_rank
        Comparator<Cancion> comparator = Comparator.comparingInt(Cancion::getDailyRank).reversed();
        // Crear el heap
        MyHeap<Cancion> cancionesHeap = new MyHeap<>(comparator);
        // Insertar canciones en el heap
        for (Cancion cancion : cancionesMap.values()) {
            cancionesHeap.insert(cancion);
        }
        // Obtener e imprimir las top 10 canciones
        List<Cancion> topCanciones = cancionesHeap.extractAllSorted();
        Collections.reverse(topCanciones);
        System.out.println("Imprimiendo top 10 de canciones para el país " + country + " en la fecha " + date);
        topCanciones.forEach(cancion -> System.out.println("Posición: " + cancion.getDailyRank() +
                " - Nombre: " + cancion.getNombre() + " - Artista: " + cancion.getArtista() ));


    }

    public static Map<String, Cancion> getCancionesGlobal(String date) {
        Map<String, Cancion> canciones = new HashMap<>();
        CSVFormat format = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreSurroundingSpaces() // Ignora espacios en blanco alrededor de los delimitadores
                .withEscape('\\') // Especifica un carácter de escape
                .withQuote('"') // Define el carácter de cita
                .withDelimiter(','); // Especifica el delimitador

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             CSVParser csvParser = new CSVParser(br, format)) {
            for (CSVRecord record : csvParser) {
                String fecha = record.get("snapshot_date");
                String recordCountry = record.get("country");
                if ((fecha.equals(date.trim()) && recordCountry.isEmpty()) || recordCountry.equals(" ")) {
                    Cancion cancion = new Cancion(
                            record.get("spotify_id"),
                            record.get("name"),
                            record.get("artists"),
                            Integer.parseInt(record.get("daily_rank")),
                            record.get("country"),
                            record.get("snapshot_date"),
                            Double.parseDouble(record.get("tempo"))
                    );
                    canciones.put(cancion.getId(), cancion);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return canciones;
    }
    public static void top5CancionesGlobal(String date) {

        Map<String, Cancion> cancionesMap = getCancionesGlobal(date);
        Comparator<Cancion> comparator = Comparator.comparingInt(Cancion::getDailyRank).reversed();
        MyHeap<Cancion> cancionesHeap = new MyHeap<>(comparator);
        for (Cancion cancion : cancionesMap.values()) {
            cancionesHeap.insert(cancion);
        }
        List<Cancion> topCanciones = cancionesHeap.extractAllSorted();
        Collections.reverse(topCanciones);
        System.out.println("Imprimiendo top 5 de canciones global en la fecha " + date);
        topCanciones.stream()
                .limit(5)  // Limita a 5 elementos
                .forEach(cancion -> System.out.println("Posición: " + cancion.getDailyRank() +
                        " - Nombre: " + cancion.getNombre() +
                        " - Artista: " + cancion.getArtista()));

    }

    public static Map<String, Artista> getArtistasGlobal(String fechaInicio, String fechaFin) {
        Map<String, Artista> artistas = new HashMap<>();
        CSVFormat format = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreSurroundingSpaces()
                .withEscape('\\')
                .withQuote('"')
                .withDelimiter(',');

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             CSVParser csvParser = new CSVParser(br, format)) {
            for (CSVRecord record : csvParser) {
                String nombreArtista = record.get("artists").trim();
                if (!artistas.containsKey(nombreArtista)) {
                    artistas.put(nombreArtista, new Artista(nombreArtista));
                }
                Artista artista = artistas.get(nombreArtista);
                artista.setApariciones(artista.getApariciones() + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return artistas;
    }
    public static void top7ArtistasMasApariciones(String fechaInicio, String fechaFin) {

        Map<String, Artista> artistas = getArtistasGlobal(fechaInicio, fechaFin);
        Comparator<Artista> comparator = Comparator.comparingInt(Artista::getApariciones);
        MyHeap<Artista> artistasHeap = new MyHeap<>(comparator);
        for (Artista artista : artistas.values()) {
            artistasHeap.insert(artista);
        }
        List<Artista> topArtistas = artistasHeap.extractAllSorted();
        Collections.reverse(topArtistas);
        System.out.println("Imprimiendo top 7 de artistas con más apariciones entre el " + fechaInicio + " y " + fechaFin);
        topArtistas.stream()
                .limit(7)
                .forEach(artista -> System.out.println("Nombre: " + artista.getNombre() +
                        " - " + "apariciones: " + artista.getApariciones()));
    }


    public static int contarAparicionesArtista(String fecha, String artistaBuscado) {
        int apariciones = 0;
        CSVFormat format = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreSurroundingSpaces()
                .withEscape('\\')
                .withQuote('"')
                .withDelimiter(',');

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             CSVParser csvParser = new CSVParser(br, format)) {
            for (CSVRecord record : csvParser) {
                String fechaRegistro = record.get("snapshot_date").trim();
                String artistas = record.get("artists");
                if (fechaRegistro.equals(fecha) && artistas.contains(artistaBuscado)) {
                    apariciones++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apariciones;

    }
    public static void imprimirAparicionesArtista(String fecha, String artista) {
        int apariciones = contarAparicionesArtista(fecha, artista);
        System.out.println("El artista " + artista + " apareció " + apariciones + " veces en la fecha " + fecha);
    }

    public static int getCancionesTempoFecha(String fechaInicio, String fechaFin, double minTempo, double maxTempo) {
        int count = 0;
        CSVFormat format = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreSurroundingSpaces()
                .withEscape('\\')
                .withQuote('"')
                .withDelimiter(',');

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             CSVParser csvParser = new CSVParser(br, format)) {
            for (CSVRecord record : csvParser) {
                String fecha = record.get("snapshot_date").trim();
                double tempo = Double.parseDouble(record.get("tempo").trim());
                if ((fecha.compareTo(fechaInicio) >= 0 && fecha.compareTo(fechaFin) <= 0) && (tempo >= minTempo && tempo <= maxTempo)) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
    public static void imprimirCountTempo(String fechaInicio, String fechaFin, Double minTempo, Double maxTempo) {

        int count = getCancionesTempoFecha(fechaInicio, fechaFin, minTempo, maxTempo);
        System.out.println("Número de canciones con un tempo entre " + minTempo + " y " + maxTempo +
                " desde " + fechaInicio + " hasta " + fechaFin + ": " + count);
    }

}