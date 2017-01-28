package com.gashe.app_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQlite sqlite = new SQlite(this, "myDataBase", null, 1);

        Persona persona1 = new Persona(1, "Juan");
        Persona persona2 = new Persona(2, "Rosa");
        Persona persona3 = new Persona(3, "Conchi");

        sqlite.insertarPersona(persona1);
        sqlite.insertarPersona(persona2);
        sqlite.insertarPersona(persona3);

        Coche coche1 = new Coche("Bmw", persona3);
        Coche coche2 = new Coche("Renault", persona1);
        Coche coche3 = new Coche("Seat", persona1);

        sqlite.insertarCoche(coche1);
        sqlite.insertarCoche(coche2);
        sqlite.insertarCoche(coche3);

        /*List<Coche> cocheList = sqlite.mostrarCochesPersona(persona1);

        for (Coche car : cocheList) {
            Log.e("TAG", "COCHE = "+car.getModelo());
        }*/

    }
}
