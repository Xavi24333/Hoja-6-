import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

public class PruebaGestor {

    private Gestor gestor;
    private Map<String, Pokemon> mapaPrueba;

    @BeforeEach
    public void setUp() throws IOException {
        mapaPrueba = new HashMap<>();
        gestor = new Gestor(mapaPrueba);

        // Crear CSV de prueba
        PrintWriter w = new PrintWriter("pokemon_test.csv");
        w.println("Name,Pokedex Number,Type1,Type2,Classification,Height (m),Weight (kg),Abilities,Generation,Legendary Status");
        w.println("FuegoMon,900,Fire,,Test Type,1.2,35,\"Blaze, Tough-claws\",1,No");
        w.println("AguaMon,901,Water,,Test Type,1.0,30,\"Torrent, Rain-dish\",1,No");
        w.close();

        gestor.cargar("pokemon_test.csv");
    }

    @Test
    public void testBuscarHab() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        gestor.buscarHab("blaze");

        String salida = out.toString().toLowerCase();
        assertTrue(salida.contains("fuegomon"));
    }

    @Test
    public void testAgregarPokemon() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        gestor.agregar("AguaMon");

        String salida = out.toString().toLowerCase();
        assertTrue(salida.contains("agregado"));
    }
}
