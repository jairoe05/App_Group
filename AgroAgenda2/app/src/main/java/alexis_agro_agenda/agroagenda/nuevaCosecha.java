package alexis_agro_agenda.agroagenda;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import android.database.DatabaseUtils;
import android.util.Log;
import java.util.Calendar;

import alexis_agro_agenda.agroagenda.R;
import alexis_agro_agenda.agroagenda.modelo.*;
import alexis_agro_agenda.agroagenda.sqlite.BaseDatosSiembro;
import alexis_agro_agenda.agroagenda.sqlite.OperacionesBaseDatos;

public class nuevaCosecha extends AppCompatActivity {

     //lista expandible tipo de cosecha
     private Spinner lista;
    String [] producto = {"Escoja el producto que va sembrar","Papa","Cebolla","Remolacha","Brócoli"};
    //numeros de socios
    private Spinner numSocio;
    String [] socios = {"Cuantos socios tiene en este siembro","1","2","3","4","5"};
    //fecha
    private int anio, mes, dia,anio2, mes2, dia2;
    private EditText fecha;
    private Button date;
    static final int DATE_ID = 0;


    //*************bd***********
    OperacionesBaseDatos datos;

    private static BaseDatosSiembro baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_cosecha);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //fecha del sistema
        fecha = (EditText) findViewById(R.id.fecha);
        date = (Button) findViewById(R.id.botonDate);

        date.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View view){
                showDialog(DATE_ID);
            }
        });

        Calendar c = Calendar.getInstance();
        anio = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);

        fecha.setText(new StringBuilder().append(dia+"/"+mes+"/"+anio));



        lista = (Spinner) findViewById(R.id.lista);
        //adactador del spinner
        ArrayAdapter<String> adactador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,producto);
        lista.setAdapter(adactador);

        lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                Toast to;
                switch (i){
                    case 1:
                        to = Toast.makeText(getApplicationContext(), "Há escogido: "+producto[i], Toast.LENGTH_LONG);
                        to.show();
                        break;
                    case 2:
                        to = Toast.makeText(getApplicationContext(), "Há escogido: "+producto[i], Toast.LENGTH_LONG);
                        to.show();
                        break;
                    case 3:
                        to = Toast.makeText(getApplicationContext(), "Há escogido: "+producto[i], Toast.LENGTH_LONG);
                        to.show();
                        break;
                    case 4:
                        to = Toast.makeText(getApplicationContext(), "Há escogido: "+producto[i], Toast.LENGTH_LONG);
                        to.show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //lista de socios

        numSocio = (Spinner) findViewById(R.id.num_socios);
        //adactador del spinner
        ArrayAdapter<String> adactador2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,socios);
        numSocio.setAdapter(adactador2);

        numSocio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                Toast to;
                // Obtención del manejador de
                FragmentManager fm = getSupportFragmentManager();
                Intent intent = new Intent(nuevaCosecha.this, nombreSocio.class);
                switch (i) {
                    case 1:

                        to = Toast.makeText(getApplicationContext(), "Há escogido: " + socios[i] + " socio", Toast.LENGTH_LONG);
                        to.show();
                        for (int a = 0;a<i;a++){


                            msjToast(a);
                            new nombreSocio().show(fm, "nombreSocio");

                        }

                        break;
                    case 2:
                        to = Toast.makeText(getApplicationContext(), "Há escogido: " + socios[i] + " socios", Toast.LENGTH_LONG);
                        to.show();
                        for (int a = 0;a<i;a++){
                            msjToast(a);
                            new nombreSocio().show(fm, "nombreSocio");
                        }
                        break;
                    case 3:
                        to = Toast.makeText(getApplicationContext(), "Há escogido: " + socios[i] + " socios", Toast.LENGTH_LONG);
                        to.show();
                        for (int a = 0;a<i;a++){
                            msjToast(a);
                            new nombreSocio().show(fm, "nombreSocio");
                        }
                        break;
                    case 4:
                        to = Toast.makeText(getApplicationContext(), "Há escogido: " + socios[i] + " socios", Toast.LENGTH_LONG);
                        to.show();
                        for (int a = 0;a<i;a++){
                            msjToast(a);
                            new nombreSocio().show(fm, "nombreSocio");
                        }
                        break;
                    case 5:
                        to = Toast.makeText(getApplicationContext(), "Há escogido: " + socios[i] + " socios", Toast.LENGTH_LONG);
                        to.show();
                        for (int a = 0;a<i;a++){
                            msjToast(a);
                            new nombreSocio().show(fm, "nombreSocio");
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //********Inicia base de datos*********************************************************

        getApplicationContext().deleteDatabase("agroAgenda.db");
        datos = OperacionesBaseDatos
                .obtenerInstancia(getApplicationContext());

      //  new TareaPruebaDatos().execute();

        }

    private void ColocarFecha(){

        fecha.setText(new StringBuilder().append(dia2+"/"+mes2+"/"+anio2));
    }


    /*
    El DatePickerDialog.OnDateSetListener escucha si el usuaio ha pulsado
    en el boton Set del witget DatePicker. Si es asi, llama a onDateSet()
    que  actualiza las variables que contienen la fecha (anio, mes , dia)
    y despues llama al metodo ColocarFecha()
     */

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth){
                    anio2 = year;
                    mes2 = monthOfYear;
                    dia2 = dayOfMonth;

                    ColocarFecha();
                }
            };


    /*
    Este método se ejecuta conado se llama al showDailod(Date_id)
    dentro del onclickListener del boton PickDate esta llamada se hace
    cuando hacemos click en el boton PickDate Si es ID coincide, se lanza
    el DatePickerDialog.
    */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        anio, mes, dia);
        }
        return null;
    }

    public void msjToast(int socio){
        Toast toast;
        toast= Toast.makeText(getApplicationContext(), "Ingrese los datos del " + socio + "° socio", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }


    public void nuevoSiembro(View view){
        String fechaActual = Calendar.getInstance().getTime().toString();



            // Inserción Formas de pago
            String siembro1 = datos.insertarSiembro(new Siembro(null, "Sabanilla",fechaActual,"papa"));

            datos.getDb().setTransactionSuccessful();


    }

    public void consultarSiembro(View view){


        SQLiteDatabase bd=baseDatos.getWritableDatabase();
        String dni="1";
        Cursor fila=bd.rawQuery("select parcela from siembro where id=1",null);
        if (fila.moveToFirst())
        {

            Toast.makeText(this,fila.getString(0) , Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "No existe una persona con dicho dni", Toast.LENGTH_SHORT).show();
        bd.close();

    }

// Tarea asíncrona interna para ejecutar las operaciones de la base de datos
    public class TareaPruebaDatos extends AsyncTask<Void, Void, Void> {



        @Override
        protected Void doInBackground(Void... params) {

            // [INSERCIONES]
            String fechaActual = Calendar.getInstance().getTime().toString();

            try {


                datos.getDb().beginTransaction();

                // Inserción Clientes
                /*
                String socio1 = datos.insertarSocio(new Socios(null, "Veronica", "Vero", 33.3));
                String socio2 = datos.insertarSocio(new Cliente(null, "Carlos", "Villagran", "4440000"));
*/

                // Inserción Formas de pago
                String siembro1 = datos.insertarSiembro(new Siembro(null, "Sabanilla",fechaActual,"papa"));



                datos.getDb().setTransactionSuccessful();
            } finally {
                datos.getDb().endTransaction();
            }

            // [QUERIES]
            Log.d("Socio","Socio");
            DatabaseUtils.dumpCursor(datos.obtenerSiembros());


            return null;
        }

    }
}
