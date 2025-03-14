import java.util.List;

public class Pokemon {
    private String nom;
    private String tipo1;
    private String tipo2;
    private List<String> habs;

    public Pokemon(String nom, String tipo1, String tipo2, List<String> habs) {
        this.nom = nom;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.habs = habs;
    }

    public String getNom() { return nom; }
    public String getTipo1() { return tipo1; }
    public String getTipo2() { return tipo2; }
    public List<String> getHabs() { return habs; }

    @Override
    public String toString() {
        return nom + " | Tipo1: " + tipo1 + " | Tipo2: " + tipo2 + " | Habilidades: " + habs;
    }
}
