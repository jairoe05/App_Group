package alexis_agro_agenda.agroagenda.sqlite;

/**
 * Created by Alexis on 13/11/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import alexis_agro_agenda.agroagenda.modelo.*;
import alexis_agro_agenda.agroagenda.sqlite.BaseDatosSiembro.Tablas;

/**
 * Clase auxiliar que implementa a {@link BaseDatosSiembro} para llevar a cabo el CRUD
 * sobre las entidades existentes.
 */

public final class OperacionesBaseDatos {

    private static BaseDatosSiembro baseDatos;

    private static OperacionesBaseDatos instancia = new OperacionesBaseDatos();

    private OperacionesBaseDatos() {
    }

    public static OperacionesBaseDatos obtenerInstancia(Context contexto) {
        if (baseDatos == null) {
            baseDatos = new BaseDatosSiembro(contexto);
        }
        return instancia;
    }
    //**************************************************************************************

    public Cursor obtenerSiembros() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        builder.setTables(SIEMBRO_JOIN_SOCIO);

        return builder.query(db, proySiembro, null, null, null, null, null);
    }

    private static final String SIEMBRO_JOIN_SOCIO = "siembro s" +
            "inner join socio so" +
            "on s.id = so.id_siembro";


    private final String[] proySiembro = new String[]{
            Tablas.SIEMBRO + "." + ContratoSiembro.Siembro.ID,
            ContratoSiembro.Siembro.PARCELA,
            ContratoSiembro.Siembro.FECHA,
            ContratoSiembro.Socio.NOMBRE,
            ContratoSiembro.Socio.APODO,
            ContratoSiembro.Siembro.PRODUCTO};

    public Cursor obtenerSiembroId(String id) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String selection = String.format("%s=?", ContratoSiembro.Siembro.ID);
        String[] selectionArgs = {id};

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(SIEMBRO_JOIN_SOCIO);

        String[] proyeccion = {
                Tablas.SIEMBRO + "." + ContratoSiembro.Siembro.ID,
                ContratoSiembro.Siembro.PARCELA,
                ContratoSiembro.Siembro.FECHA,
                ContratoSiembro.Socio.NOMBRE,
                ContratoSiembro.Socio.APODO,
                ContratoSiembro.Siembro.PRODUCTO};

        return builder.query(db, proyeccion, selection, selectionArgs, null, null, null);
    }

    public String insertarSiembro(Siembro pedido) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        // Generar Pk
        String idSiembro = ContratoSiembro.Siembro.generarIdSiembro();

        //String idSiembro = "1";

        ContentValues valores = new ContentValues();
        valores.put(ContratoSiembro.Siembro.ID, idSiembro);
        valores.put(ContratoSiembro.Siembro.FECHA, pedido.fecha);
        valores.put(ContratoSiembro.Siembro.PARCELA, pedido.parcela);
        valores.put(ContratoSiembro.Siembro.PRODUCTO, pedido.producto);

        // Insertar Siembro
    db.insertOrThrow(Tablas.SIEMBRO, null, valores);

        return idSiembro;
    }

    public boolean actualizarSiembro (Siembro pedidoNuevo) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(ContratoSiembro.Siembro.FECHA, pedidoNuevo.fecha);
        valores.put(ContratoSiembro.Siembro.PARCELA, pedidoNuevo.parcela);
        valores.put(ContratoSiembro.Siembro.PRODUCTO, pedidoNuevo.producto);

        String whereClause = String.format("%s=?", ContratoSiembro.Siembro.ID);
        String[] whereArgs = {pedidoNuevo.id_siembro};

        int resultado = db.update(Tablas.SIEMBRO, valores, whereClause, whereArgs);

        return resultado > 0;
    }


    public boolean eliminarSiembro(String idSiembro) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String whereClause = ContratoSiembro.Siembro.ID + "=?";
        String[] whereArgs = {idSiembro};

        int resultado = db.delete(Tablas.SIEMBRO, whereClause, whereArgs);

        return resultado > 0;
    }

    //*************************************************************************************


    public Cursor obtenerSocio() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        builder.setTables(SOCIO_JOIN_SIEMBRO);

        return builder.query(db, proySocio, null, null, null, null, null);
    }

    private static final String SOCIO_JOIN_SIEMBRO = "siembro s" +
            "inner join socio so" +
            "on s.id = so.id_siembro";


    private final String[] proySocio = new String[]{
            Tablas.SOCIO + "." + ContratoSiembro.Socio.ID,
            ContratoSiembro.Socio.ID_SIEMBRO,
            ContratoSiembro.Socio.NOMBRE,
            ContratoSiembro.Socio.APODO,
            ContratoSiembro.Socio.PORCENTAGE};

    public Cursor obtenerSocioId(String id) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String selection = String.format("%s=?", ContratoSiembro.Socio.ID);
        String[] selectionArgs = {id};

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(SOCIO_JOIN_SIEMBRO);

        String[] proyeccion = {
                Tablas.SOCIO + "." + ContratoSiembro.Socio.ID,
                ContratoSiembro.Socio.ID_SIEMBRO,
                ContratoSiembro.Socio.NOMBRE,
                ContratoSiembro.Socio.APODO,
                ContratoSiembro.Socio.PORCENTAGE};

        return builder.query(db, proyeccion, selection, selectionArgs, null, null, null);
    }

    public String insertarSocio(Socios socios) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        // Generar Pk
        String idSocio = ContratoSiembro.Socio.generarIdSocio();

        ContentValues valores = new ContentValues();
        valores.put(ContratoSiembro.Socio.ID, idSocio);
        valores.put(ContratoSiembro.Socio.NOMBRE, socios.nombre);
        valores.put(ContratoSiembro.Socio.APODO, socios.apodo);
        valores.put(ContratoSiembro.Socio.PORCENTAGE, socios.porsentage);

        // Insertar cabecera
        db.insertOrThrow(Tablas.SOCIO, null, valores);

        return idSocio;
    }

    public boolean actualizarSocio (Socios socioNuevo) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(ContratoSiembro.Socio.NOMBRE, socioNuevo.nombre);
        valores.put(ContratoSiembro.Socio.APODO, socioNuevo.apodo);
        valores.put(ContratoSiembro.Socio.PORCENTAGE, socioNuevo.porsentage);

        String whereClause = String.format("%s=?", ContratoSiembro.Siembro.ID);
        String[] whereArgs = {socioNuevo.id};

        int resultado = db.update(Tablas.SOCIO, valores, whereClause, whereArgs);

        return resultado > 0;
    }


    public boolean eliminarSocio(String idSocio) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String whereClause = ContratoSiembro.Socio.ID + "=?";
        String[] whereArgs = {idSocio};

        int resultado = db.delete(Tablas.SOCIO, whereClause, whereArgs);

        return resultado > 0;
    }


    public SQLiteDatabase getDb() {
        return baseDatos.getWritableDatabase();
    }
}