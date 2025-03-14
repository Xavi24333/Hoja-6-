import java.util.*;

public class Mapas {
    public static Map<String, Pokemon> obtenerMapa(String id) {
      
        if (id.equals("1")) {
            return new HashMap<>();
        } else if (id.equals("2")) {
            return new TreeMap<>();
        } else if (id.equals("3")) {
            return new LinkedHashMap<>();
        } else {
            return new HashMap<>();
        }
    }
}
