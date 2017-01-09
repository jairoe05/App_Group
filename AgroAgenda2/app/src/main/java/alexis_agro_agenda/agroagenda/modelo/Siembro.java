package alexis_agro_agenda.agroagenda.modelo;

/**
 * Created by Alexis on 13/11/2016.
 */
public class Siembro {

    public String id_siembro;
    public String parcela;
    public String fecha;
    public String producto;

    public Siembro(String id_siembro, String parcela, String fecha, String producto) {
        this.id_siembro = id_siembro;
        this.parcela = parcela;
        this.fecha = fecha;
        this.producto = producto;
    }


}
