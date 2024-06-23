package uy.edu.um;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import uy.edu.um.entities.SpotifyApp;

public class Main {

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

        System.out.println("Ingrese el país: ");
        String country = scanner.nextLine();
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
