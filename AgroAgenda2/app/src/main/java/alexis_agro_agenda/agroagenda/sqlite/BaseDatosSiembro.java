package alexis_agro_agenda.agroagenda.sqlite;

/**
 * Created by Alexis on 21/11/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

import alexis_agro_agenda.agroagenda.sqlite.ContratoSiembro.Inversion;
import alexis_agro_agenda.agroagenda.sqlite.ContratoSiembro.Siembro;
import alexis_agro_agenda.agroagenda.sqlite.ContratoSiembro.Venta;
import alexis_agro_agenda.agroagenda.sqlite.ContratoSiembro.Socio;


/**
 * Clase que administra la conexión de la base de datos y su estructuración
 */

public class BaseDatosSiembro extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DATOS = "agroAgenda.db";

    private static final int VERSION_ACTUAL = 1;

    private final Context contexto;

    interface Tablas {
        String SIEMBRO = "siembro";
        String SOCIO = "socio";
        String INVERSION = "inversion";
        String VENTA = "venta";
    }

    interface Referencias {

        String ID_SIEMBRO = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                Tablas.SIEMBRO, Siembro.ID);

        String ID_SOCIO = String.format("REFERENCES %s(%s)",
                Tablas.SOCIO, Socio.ID);

        String ID_INVERSION = String.format("REFERENCES %s(%s)",
                Tablas.INVERSION, Inversion.ID);

        String ID_VENTA = String.format("REFERENCES %s(%s)",
                Tablas.VENTA, Venta.ID);
    }

    public BaseDatosSiembro(Context contexto) {
        super(contexto, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
        this.contexto = contexto;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT  NOT NULL,%s DATETIME NOT NULL,%s TEXT NOT NULL )",
                Tablas.SIEMBRO, BaseColumns._ID,
                Siembro.ID,
                Siembro.PARCELA,
                Siembro.FECHA,
                Siembro.PRODUCTO));

        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s INTEGER NOT NULL %s,%s TEXT NOT NULL  ,%s TEXT NOT NULL  ," +
                        "%s REAL NOT NULL)",
                Tablas.SOCIO, BaseColumns._ID,
                Socio.ID,
                Socio.ID_SIEMBRO, Referencias.ID_SIEMBRO,
                Socio.NOMBRE,
                Socio.APODO,
                Socio.PORCENTAGE));

        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s INTEGER NOT NULL %s,%s TEXT NOT NULL,%s TEXT NOT NULL," +
                        "%s NUMERIC NOT NULL )",
                Tablas.INVERSION, BaseColumns._ID,
                Inversion.ID,
                Inversion.ID_SIEMBRO, Referencias.ID_SIEMBRO,
                Inversion.TIPO,
                Inversion.DETALLE,
                Inversion.COSTO));

        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s INTEGER NOT NULL %s, %s INTEGER NOT NULL ,%s NUMERIC NOT NULL )",
                Tablas.VENTA, BaseColumns._ID,
                Venta.ID,
                Venta.ID_SIEMBRO, Referencias.ID_SIEMBRO,
                Venta.CANTIDAD,
                Venta.PRECIO));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Tablas.SIEMBRO);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.SOCIO);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.INVERSION);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.VENTA);

        onCreate(db);
    }


}
