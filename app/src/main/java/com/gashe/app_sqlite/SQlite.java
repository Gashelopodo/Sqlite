package com.gashe.app_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cice on 28/1/17.
 */

public class SQlite extends SQLiteOpenHelper{

    private static final String SQL_CREA_TABLA_PERSONAS = "CREATE TABLE PERSONA (id INTEGER PRIMARY KEY, nombre TEXT)";
    private static final String SQL_CREA_TABLA_COCHES = "CREATE TABLE COCHE (id INTEGER PRIMARY KEY AUTOINCREMENT, idpersona INTEGER, modelo TEXT, FOREIGN KEY (idpersona) REFERENCES PERSONA (id))";


    public SQlite(Context context, String nombre, SQLiteDatabase.CursorFactory cursor, int version){
        super(context, nombre, cursor, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(getClass().getCanonicalName(), "Entro");
        sqLiteDatabase.execSQL(SQL_CREA_TABLA_PERSONAS);
        sqLiteDatabase.execSQL(SQL_CREA_TABLA_COCHES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertarCoche(Coche coche){
        SQLiteDatabase query = this.getWritableDatabase();
        query.execSQL("INSERT INTO COCHE (modelo, idpersona) VALUES ('"+coche.getModelo()+"', "+coche.getPersona().getId()+")");
        query.close();
    }

    public void insertarPersona(Persona persona){
        SQLiteDatabase query = this.getWritableDatabase();
        query.execSQL("INSERT INTO PERSONA (id, nombre) VALUES ('"+persona.getId()+"', "+persona.getNombre()+")");
        query.close();
    }

    public List<Coche> mostrarCochesPersona(Persona persona){
        List<Coche> cocheList = null;

        SQLiteDatabase query = this.getReadableDatabase();
        Cursor cursor = query.rawQuery("SELECT modelo FROM COCHE WHERE idpersona = "+persona.getId(), null);

        if((cursor != null) && (cursor.getCount() > 0)){

            cursor.moveToFirst();
            cocheList = new ArrayList<Coche>();
            String modelo = null;
            Coche coche = null;

            do {
                modelo = cursor.getString(0);
                coche = new Coche(modelo);
                cocheList.add(coche);
            }while (cursor.moveToNext());

            cursor.close();
        }

        query.close();

        return cocheList;
    }
}
