package com.example.diegovistoso.ironfly2;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class cargactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargactivity);

        //quitar barra de notificaciones
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        Thread thread= new Thread(){

            @Override
            public void run() {
                //se muestra la pantalla de carga
                try{
                sleep(5000);
                }
                catch (Exception e){
                }
                finally {
                    //despues de cargar manda al nivel 2
                    Intent n2intent = new Intent(cargactivity.this, Main3Activity.class);
                    startActivity(n2intent);
                }
            }
        };
        thread.start();
    }
}
