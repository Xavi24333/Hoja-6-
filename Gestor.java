import java.io.*;
import java.util.*;

public class Gestor {
    private Map<String, Pokemon> mapa;
    private List<Pokemon> colUsu = new ArrayList<>();

    public Gestor(Map<String, Pokemon> m) {
        this.mapa = m;
    }

    public void cargar(String archivo) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader(archivo));
        String l;
        r.readLine(); // Saltar encabezado
        while ((l = r.readLine()) != null) {
            // Cortar las partes que sí se pueden separar con seguridad
            String[] partes = l.split(",", -1);
            if (partes.length < 7) continue;

            String nom = partes[0].trim();
            String tipo1 = partes[2].trim();
            String tipo2 = partes[3].trim();

            // Buscar índice del campo "Abilities"
            int index = l.indexOf("\"");
            int fin = l.indexOf("\"", index + 1);
            String habsRaw = "";
            if (index != -1 && fin != -1) {
                habsRaw = l.substring(index + 1, fin);
            }

            List<String> habs = new ArrayList<>();
            for (String h : habsRaw.split(",")) {
                habs.add(h.trim().toLowerCase());
            }

            Pokemon p = new Pokemon(nom, tipo1, tipo2, habs);
            mapa.put(nom, p);
        }
        r.close();
    }

    public void agregar(String nom) {
        if (!mapa.containsKey(nom)) {
            System.out.println("Ese no está.");
            return;
        }
        for (Pokemon p : colUsu) {
            if (p.getNom().equalsIgnoreCase(nom)) {
                System.out.println("Ya lo tenés.");
                return;
            }
        }
        colUsu.add(mapa.get(nom));
        System.out.println("Agregado.");
    }

    public void verUno(String nom) {
        Pokemon p = mapa.get(nom);
        if (p != null) {
            System.out.println(p);
        } else {
            System.out.println("Nada.");
        }
    }

    public void verColUsu() {
        colUsu.stream()
            .sorted(Comparator.comparing(Pokemon::getTipo1))
            .forEach(p -> System.out.println(p.getNom() + " | " + p.getTipo1()));
    }

    public void verTodos() {
        mapa.values().stream()
            .sorted(Comparator.comparing(Pokemon::getTipo1))
            .forEach(p -> System.out.println(p.getNom() + " | " + p.getTipo1()));
    }

    public void buscarHab(String entrada) {
        boolean ok = false;
        String[] partes = entrada.toLowerCase().split(",");
        List<String> buscadas = new ArrayList<>();
        for (String h : partes) {
            buscadas.add(h.trim());
        }

        for (Pokemon p : mapa.values()) {
            for (String habPk : p.getHabs()) {
                for (String b : buscadas) {
                    if (habPk.contains(b)) {
                        System.out.println(p);
                        ok = true;
                        break;
                    }
                }
                if (ok) break;
            }
        }

        if (!ok) {
            System.out.println("No se ha encontrado.");
        }
    }
}
