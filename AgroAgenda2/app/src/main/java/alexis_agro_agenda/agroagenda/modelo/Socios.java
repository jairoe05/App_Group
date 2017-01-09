package alexis_agro_agenda.agroagenda.modelo;

/**
 * Created by Alexis on 13/11/2016.
 */
public class Socios {

    public String id;
    public int id_siembro;
    public String nombre;
    public String apodo;
    public double porsentage;

    public Socios(String id, int id_siembro, String nombre, String apodo, double porsentage) {
        this.id = id;
        this.id_siembro = id_siembro;
        this.nombre = nombre;
        this.apodo = apodo;
        this.porsentage = porsentage;
    }
}
