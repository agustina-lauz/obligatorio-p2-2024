package uy.edu.um;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

import uy.edu.um.entities.SpotifyApp;

public class Main {

    private static Map<String, String> countriesMap = new HashMap<>();
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

        private static final SpotifyApp app = new SpotifyApp();
        public static void main(String[] args) throws IOException {
            Runtime rt = Runtime.getRuntime();
            long total_mem = rt.totalMemory();
            long loadStartTime = System.currentTimeMillis();
            measureMemoryAndTime(rt, total_mem, loadStartTime);
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;
            while (!exit) {
                System.out.println("==== Menú ====");
                System.out.println("1. Top 10 canciones en un país en un día dado");
                System.out.println("2. Top 5 canciones que aparecen en el top 50 global en un día dado.");
                System.out.println("3. Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado.");
                System.out.println("4. Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada.");
                System.out.println("5. Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas.");
                System.out.println("0. Salir");
                System.out.println("==============");

                System.out.print("Ingrese el número de la opción deseada: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1 -> printTop10Pais();
                    case 2 -> printTop5Global();
                    case 3 -> printTop7Artista();
                    case 4 -> printCountArtista();
                    case 5 -> printCountTempo();
                    case 0 -> exit = true;
                    default -> System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                }
            }
        }

    private static void printTop10Pais() {
        Scanner scanner = new Scanner(System.in);
        String date;
        while (true) {
            System.out.println("Ingrese la fecha en formato: DD/MM/YYYY");
            date = scanner.nextLine();
            if (isValidDate(date)) break;
            System.out.println("Fecha inválida. Intente nuevamente.");
        }

        String country = "";
        boolean validCountry = false;
        while (!validCountry) {
            System.out.println("Ingrese el país: ");
            country = scanner.nextLine().toLowerCase();
            if (countriesMap.containsKey(country)) {
                validCountry = true;
            } else {
                System.out.println("País inválido. Por favor, ingrese un país válido.");
            }
        }
        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long loadStartTime = System.currentTimeMillis();
        app.top10CancionesPais(date, country);
        measureMemoryAndTime(rt, total_mem, loadStartTime);
    }

    private static void printTop5Global() {
        Scanner scanner = new Scanner(System.in);
        String date;
        while (true) {
            System.out.println("Ingrese la fecha en formato: DD/MM/YYYY");
            date = scanner.nextLine();
            if (isValidDate(date)) break;
            System.out.println("Fecha inválida. Intente nuevamente.");
        }
        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long loadStartTime = System.currentTimeMillis();
        app.top5CancionesGlobal(date);
        measureMemoryAndTime(rt, total_mem, loadStartTime);
    }

    private static void printTop7Artista() {
        Scanner scanner = new Scanner(System.in);
        String dateStart, dateEnd;

        while (true) {
            System.out.println("Ingrese la fecha desde en formato: DD/MM/YYYY");
            dateStart = scanner.nextLine();
            if (isValidDate(dateStart)) break;
            System.out.println("Fecha inválida. Intente nuevamente.");
        }

        while (true) {
            System.out.println("Ingrese la fecha hasta en formato: DD/MM/YYYY");
            dateEnd = scanner.nextLine();
            if (isValidDate(dateEnd)) break;
            System.out.println("Fecha inválida. Intente nuevamente.");
        }
        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long loadStartTime = System.currentTimeMillis();
        app.top7ArtistasMasApariciones(dateStart, dateEnd);
        measureMemoryAndTime(rt, total_mem, loadStartTime);
    }

    private static void printCountArtista() {
        Scanner scanner = new Scanner(System.in);
        String date;

        while (true) {
            System.out.println("Ingrese la fecha desde en formato: DD/MM/YYYY");
            date = scanner.nextLine();
            if (isValidDate(date)) break;
            System.out.println("Fecha inválida. Intente nuevamente.");
        }

        System.out.println("Ingrese el nombre del artista: ");
        String artist = scanner.nextLine();

        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long loadStartTime = System.currentTimeMillis();
        app.imprimirAparicionesArtista(date, artist);
        measureMemoryAndTime(rt, total_mem, loadStartTime);

    }

    private static void printCountTempo() {
        Scanner scanner = new Scanner(System.in);
        String dateStart, dateEnd;

        while (true) {
            System.out.println("Ingrese la fecha desde en formato: DD/MM/YYYY");
            dateStart = scanner.nextLine();
            if (isValidDate(dateStart)) break;
            System.out.println("Fecha inválida. Intente nuevamente.");
        }

        while (true) {
            System.out.println("Ingrese la fecha hasta en formato: DD/MM/YYYY");
            dateEnd = scanner.nextLine();
            if (isValidDate(dateEnd)) break;
            System.out.println("Fecha inválida. Intente nuevamente.");
        }

        double tempoMin, tempoMax;

        while (true) {
            System.out.println("Ingrese el tempo mínimo:");
            if (scanner.hasNextDouble()) {
                tempoMin = scanner.nextDouble();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Valor inválido. Intente nuevamente.");
                scanner.nextLine();
            }
        }

        while (true) {
            System.out.println("Ingrese el tempo máximo:");
            if (scanner.hasNextDouble()) {
                tempoMax = scanner.nextDouble();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Valor inválido. Intente nuevamente.");
                scanner.nextLine();
            }
        }
        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long loadStartTime = System.currentTimeMillis();
        app.imprimirCountTempo(dateStart, dateEnd, tempoMin, tempoMax);
        measureMemoryAndTime(rt, total_mem, loadStartTime);
        }

    private static boolean isValidDate(String date) {
        return Pattern.matches("\\d{2}/\\d{2}/\\d{4}", date);
    }

    private static void measureMemoryAndTime(Runtime rt, long total_mem, long loadStartTime) {
        long free_mem = rt.freeMemory();
        long used_mem = total_mem - free_mem;
        long loadEndTime = System.currentTimeMillis();
        long loadTime = loadEndTime - loadStartTime;
        System.out.println("------------------------------------------------------------");
        System.out.println("Tiempo de carga: " + loadTime + " ms");
        System.out.println("------------------------------------------------------------");
        System.out.println("Memoria usada: " + used_mem / 1000000 + " megabytes");
        System.out.println("------------------------------------------------------------");
    }


}
