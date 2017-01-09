package alexis_agro_agenda.agroagenda.modelo;

/**
 * Created by Alexis on 13/11/2016.
 */
public class Inversion {
    public int id;
    public int id_siembro;
    public String tipo;
    public String detalle;
    public double costo;

    public Inversion(int id, int id_siembro, String tipo, String detalle, double costo) {
        this.id = id;
        this.id_siembro = id_siembro;
        this.tipo = tipo;
        this.detalle = detalle;
        this.costo = costo;
    }
}
