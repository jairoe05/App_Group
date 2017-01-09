package alexis_agro_agenda.agroagenda.modelo;

/**
 * Created by Alexis on 13/11/2016.
 */
public class Venta {
    public int id;
    public int id_siembro;
    public double cantidad;
    public double precio;

    public Venta(int id, int id_siembro, double cantidad, double precio) {
        this.id = id;
        this.id_siembro = id_siembro;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}
