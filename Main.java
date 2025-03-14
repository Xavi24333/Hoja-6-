import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String t = "";
        while (true) {
            System.out.println("Tipo de mapa:");
            System.out.println("1. HashMap");
            System.out.println("2. TreeMap");
            System.out.println("3. LinkedHashMap");
            System.out.print(": ");
            String op = s.nextLine();
            if (op.equals("1")) { t = "1"; break; }
            else if (op.equals("2")) { t = "2"; break; }
            else if (op.equals("3")) { t = "3"; break; }
            else System.out.println("Otra vez.");
        }

        Map<String, Pokemon> m = Mapas.obtenerMapa(t);
        Gestor g = new Gestor(m);

        try {
            g.cargar("pokemon.csv");
        } catch (Exception e) {
            System.out.println("Error.");
            return;
        }

        int o = 0;
        do {
            System.out.println("\n1. Agregar algun Pokemon");
            System.out.println("2. Ver cualquier Pokemon");
            System.out.println("3. Ver colección de Tipo 1");
            System.out.println("4. Ver todos los pokemones de tipo 1 ");
            System.out.println("5. Buscar habilidad del pokemon");
            System.out.println("6. Salir");
            System.out.print(": ");
            String inp = s.nextLine();
            try { o = Integer.parseInt(inp); } catch (Exception ex) { o = -1; }

            switch (o) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    g.agregar(s.nextLine());
                }
                case 2 -> {
                    System.out.print("Nombre: ");
                    g.verUno(s.nextLine());
                }
                case 3 -> g.verColUsu();
                case 4 -> g.verTodos();
                case 5 -> {
                    System.out.print("Habilidad: ");
                    g.buscarHab(s.nextLine());
                }
                case 6 -> System.out.println("Bye");
                default -> System.out.println("Nada.");
            }
        } while (o != 6);

        s.close();
    }
}
