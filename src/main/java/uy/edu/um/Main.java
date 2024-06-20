package uy.edu.um;

import java.io.IOException;
import java.util.Scanner;
import uy.edu.um.entities.SpotifyApp;

public class Main {

        private static final SpotifyApp app = new SpotifyApp();
        public static void main(String[] args) throws IOException {
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


}
