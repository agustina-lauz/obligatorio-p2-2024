package uy.edu.um.entities;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.tads.heap.MyHeap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
                if (fecha.equals(date) && recordCountry.equals(country)){
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

        String countryCode = getCountryCode(country.trim());;

        // Obtener canciones filtradas por fecha y país
        Map<String, Cancion> cancionesMap = getCancionesPais(date, countryCode);
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
        System.out.println("------------------------------------------------------------");
        System.out.println("Imprimiendo top 10 de canciones para el país " + country + " en la fecha " + date);
        System.out.println("------------------------------------------------------------");
        topCanciones.forEach(cancion -> System.out.println("Posición: " + cancion.getDailyRank() +
                " - Nombre: " + cancion.getNombre() + " - Artista: " + cancion.getArtista() ));


    }

    public static List<Cancion> getCancionesGlobal(String date) {
        Map<String, Integer> songCountMap = new HashMap<>();
        Map<String, Cancion> cancionesMap = new HashMap<>();

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
                if (fecha.equals(date)) {
                    String songId = record.get("spotify_id");
                    Cancion cancion = new Cancion(
                            songId,
                            record.get("name"),
                            record.get("artists"),
                            Integer.parseInt(record.get("daily_rank")),
                            record.get("country"),
                            record.get("snapshot_date"),
                            Double.parseDouble(record.get("tempo"))
                    );

                    songCountMap.merge(songId, 1, Integer::sum);
                    cancionesMap.put(songId, cancion);
                }
            }

            // Ordenar las canciones por el conteo y devolver las primeras 5
            return songCountMap.entrySet().stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                    .limit(5)
                    .map(entry -> cancionesMap.get(entry.getKey()))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList(); // Devuelve una lista vacía si no se encuentra ninguna canción
    }
    public static void top5CancionesGlobal(String date) {

        List<Cancion> resultado = getCancionesGlobal(date);
        System.out.println("------------------------------------------------------------");
        System.out.println("Imprimiendo las 5 canciones que más aparecen en los top 50:");
        System.out.println("------------------------------------------------------------");
        resultado.forEach(cancion -> System.out.println("Canción: " + cancion.getNombre()));

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
        System.out.println("------------------------------------------------------------");
        System.out.println("Imprimiendo top 7 de artistas con más apariciones entre el " + fechaInicio + " y " + fechaFin);
        System.out.println("------------------------------------------------------------");
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
                String artistas = record.get("artists").trim().toLowerCase();
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

        String lowerCaseArtista = artista.trim().toLowerCase();
        int apariciones = contarAparicionesArtista(fecha, lowerCaseArtista);
        System.out.println("------------------------------------------------------------");
        System.out.println("El artista " + artista + " apareció " + apariciones + " veces en la fecha " + fecha);
        System.out.println("------------------------------------------------------------");
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
        System.out.println("------------------------------------------------------------");
        System.out.println("Número de canciones con un tempo entre " + minTempo + " y " + maxTempo +
                " desde " + fechaInicio + " hasta " + fechaFin + ": " + count);
        System.out.println("------------------------------------------------------------");
    }

    public static Map<String, String> countriesMap = new HashMap<>();

        static {
            countriesMap.put("uruguay", "UY");
            countriesMap.put("argentina", "AR");
            countriesMap.put("brasil", "BR");
            countriesMap.put("chile", "CL");
            countriesMap.put("paraguay", "PY");
            countriesMap.put("bolivia", "BO");
            countriesMap.put("peru", "PE");
            countriesMap.put("ecuador", "EC");
            countriesMap.put("colombia", "CO");
            countriesMap.put("venezuela", "VE");
            countriesMap.put("panama", "PA");
            countriesMap.put("costa rica", "CR");
            countriesMap.put("nicaragua", "NI");
            countriesMap.put("honduras", "HN");
            countriesMap.put("el salvador", "SV");
            countriesMap.put("guatemala", "GT");
            countriesMap.put("mexico", "MX");
            countriesMap.put("estados unidos", "US");
            countriesMap.put("canada", "CA");
            countriesMap.put("reino unido", "GB");
            countriesMap.put("españa", "ES");
            countriesMap.put("francia", "FR");
            countriesMap.put("alemania", "DE");
            countriesMap.put("italia", "IT");
            countriesMap.put("portugal", "PT");
            countriesMap.put("suecia", "SE");
            countriesMap.put("noruega", "NO");
            countriesMap.put("dinamarca", "DK");
            countriesMap.put("finlandia", "FI");
            countriesMap.put("islandia", "IS");
            countriesMap.put("australia", "AU");
            countriesMap.put("nueva zelanda", "NZ");
            countriesMap.put("japon", "JP");
            countriesMap.put("corea del sur", "KR");
            countriesMap.put("china", "CN");
            countriesMap.put("india", "IN");
            countriesMap.put("rusia", "RU");
            countriesMap.put("sudafrica", "ZA");
            countriesMap.put("nigeria", "NG");
            countriesMap.put("egipto", "EG");
            countriesMap.put("turquia", "TR");
            countriesMap.put("grecia", "GR");
            countriesMap.put("emiratos arabes unidos", "AE");
            countriesMap.put("arabia saudita", "SA");
            countriesMap.put("vietnam", "VN");
            countriesMap.put("ucrania", "UA");
            countriesMap.put("taiwan", "TW");
            countriesMap.put("tailandia", "TH");
            countriesMap.put("eslovaquia", "SK");
            countriesMap.put("singapur", "SG");
            countriesMap.put("rumania", "RO");
            countriesMap.put("polonia", "PL");
            countriesMap.put("pakistan", "PK");
            countriesMap.put("filipinas", "PH");
            countriesMap.put("marruecos", "MA");
            countriesMap.put("letonia", "LV");
            countriesMap.put("luxemburgo", "LU");
            countriesMap.put("lituania", "LT");
            countriesMap.put("kazajistan", "KZ");
            countriesMap.put("hong kong", "HK");
            countriesMap.put("hungria", "HU");
            countriesMap.put("israel", "IL");
            countriesMap.put("irlanda", "IE");
            countriesMap.put("indonesia", "ID");
            countriesMap.put("belgica", "BE");
            countriesMap.put("bulgaria", "BG");
            countriesMap.put("suiza", "CH");
            countriesMap.put("chequia", "CZ");
            countriesMap.put("republica dominicana", "DO");
            countriesMap.put("estonia", "EE");
            countriesMap.put("croacia", "HR");
            countriesMap.put("chipre", "CY");
            countriesMap.put("bielorrusia", "BY");
            countriesMap.put("afganistán", "AF");
            countriesMap.put("albania", "AL");
            countriesMap.put("argelia", "DZ");
            countriesMap.put("andorra", "AD");
            countriesMap.put("angola", "AO");
            countriesMap.put("antigua y barbuda", "AG");
            countriesMap.put("armenia", "AM");
            countriesMap.put("azerbaiyán", "AZ");
            countriesMap.put("bahamas", "BS");
            countriesMap.put("baréin", "BH");
            countriesMap.put("bangladés", "BD");
            countriesMap.put("barbados", "BB");
            countriesMap.put("benín", "BJ");
            countriesMap.put("bután", "BT");
            countriesMap.put("botsuana", "BW");
            countriesMap.put("brunéi", "BN");
            countriesMap.put("burkina faso", "BF");
            countriesMap.put("burundi", "BI");
            countriesMap.put("camboya", "KH");
            countriesMap.put("camerún", "CM");
            countriesMap.put("cabo verde", "CV");
            countriesMap.put("república centroafricana", "CF");
            countriesMap.put("chad", "TD");
            countriesMap.put("comoras", "KM");
            countriesMap.put("congo", "CG");
            countriesMap.put("república democrática del congo", "CD");
            countriesMap.put("yibuti", "DJ");
            countriesMap.put("dominica", "DM");
            countriesMap.put("timor oriental", "TL");
            countriesMap.put("guinea ecuatorial", "GQ");
            countriesMap.put("eritrea", "ER");
            countriesMap.put("etiopía", "ET");
            countriesMap.put("fiyi", "FJ");
            countriesMap.put("gabón", "GA");
            countriesMap.put("gambia", "GM");
            countriesMap.put("georgia", "GE");
            countriesMap.put("ghana", "GH");
            countriesMap.put("granada", "GD");
            countriesMap.put("guinea", "GN");
            countriesMap.put("guyana", "GY");
            countriesMap.put("haití", "HT");
            countriesMap.put("jamaica", "JM");
            countriesMap.put("jordania", "JO");
            countriesMap.put("kenia", "KE");
            countriesMap.put("kiribati", "KI");
            countriesMap.put("kuwait", "KW");
            countriesMap.put("kirguistán", "KG");
            countriesMap.put("laos", "LA");
            countriesMap.put("líbano", "LB");
            countriesMap.put("lesoto", "LS");
            countriesMap.put("liberia", "LR");
            countriesMap.put("libia", "LY");
            countriesMap.put("malasia", "MY");
            countriesMap.put("maldivas", "MV");
            countriesMap.put("mali", "ML");
            countriesMap.put("malta", "MT");
            countriesMap.put("islas marshall", "MH");
            countriesMap.put("mauritania", "MR");
            countriesMap.put("micronesia", "FM");
            countriesMap.put("moldavia", "MD");
            countriesMap.put("mónaco", "MC");
            countriesMap.put("mongolia", "MN");
            countriesMap.put("montenegro", "ME");
            countriesMap.put("mozambique", "MZ");
            countriesMap.put("birmania", "MM");
            countriesMap.put("namibia", "NA");
            countriesMap.put("nauru", "NR");
            countriesMap.put("nepal", "NP");
            countriesMap.put("níger", "NE");
            countriesMap.put("omán", "OM");
            countriesMap.put("palaos", "PW");
            countriesMap.put("paraguay", "PY");
            countriesMap.put("qatar", "QA");
            countriesMap.put("ruanda", "RW");
            countriesMap.put("senegal", "SN");
            countriesMap.put("seychelles", "SC");
            countriesMap.put("sierra leona", "SL");
            countriesMap.put("eslovenia", "SI");
            countriesMap.put("islas salomón", "SB");
            countriesMap.put("somalia", "SO");
            countriesMap.put("sudán", "SD");
            countriesMap.put("sudán del sur", "SS");
            countriesMap.put("surinam", "SR");
            countriesMap.put("esuatini", "SZ");
            countriesMap.put("siria", "SY");
            countriesMap.put("tayikistán", "TJ");
            countriesMap.put("tanzania", "TZ");
            countriesMap.put("tonga", "TO");
            countriesMap.put("trinidad y tobago", "TT");
            countriesMap.put("turkmenistán", "TM");
            countriesMap.put("tuvalu", "TV");
            countriesMap.put("uganda", "UG");
            countriesMap.put("uzbekistán", "UZ");
            countriesMap.put("vanuatu", "VU");
            countriesMap.put("ciudad del vaticano", "VA");
            countriesMap.put("yemen", "YE");
            countriesMap.put("zambia", "ZM");
            countriesMap.put("zimbabue", "ZW");

        }

    public static String getCountryCode(String countryName) {
        return countriesMap.getOrDefault(countryName.trim().toLowerCase(), countryName);
    }

}