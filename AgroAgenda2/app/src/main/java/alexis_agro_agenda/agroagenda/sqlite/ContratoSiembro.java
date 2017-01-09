package alexis_agro_agenda.agroagenda.sqlite;

/**
 * Created by Alexis on 21/11/2016.
 */

import java.util.UUID;

public class ContratoSiembro {


    interface ColumnasSiembro {
        String ID = "id";
        String PARCELA = "parcela";
        String FECHA = "fecha";
        String PRODUCTO = "producto";
    }

    interface ColumnasSocio {
        String ID = "id";
        String ID_SIEMBRO = "id_siembro";
        String NOMBRE = "nombre";
        String APODO = "apodo";
        String PORCENTAGE = "porcentage";
    }

    interface ColumnasInversion {
        String ID = "id";
        String ID_SIEMBRO = "id_siembro";
        String TIPO = "tipo";
        String DETALLE = "detalle";
        String COSTO = "costo";
    }

    interface ColumnasVenta {
        String ID = "id";
        String ID_SIEMBRO = "id_siembro";
        String CANTIDAD = "cantidad";
        String PRECIO = "precio";
    }



    public static class Siembro implements ColumnasSiembro {
        public static String generarIdSiembro() {
            return "SI-" + UUID.randomUUID().toString();
        }
    }

   /* public static class DetallesPedido implements ColumnasSocio {
        // Métodos auxiliares
    }*/

    public static class Socio implements ColumnasSocio{
        public static String generarIdSocio() {
            return "SO-" + UUID.randomUUID().toString();
        }
    }

    public static class Inversion implements ColumnasInversion{
        public static String generarIdInversion() {
            return "IN-" + UUID.randomUUID().toString();
        }
    }

    public static class Venta implements ColumnasVenta{
        public static String generarIdVenta() {
            return "VE-" + UUID.randomUUID().toString();
        }
    }


    private ContratoSiembro() {
    }

}


