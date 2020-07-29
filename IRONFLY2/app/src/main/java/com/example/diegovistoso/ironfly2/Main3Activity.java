package com.example.diegovistoso.ironfly2;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class Main3Activity extends AppCompatActivity {

    private draw2 gameview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //LLAMA A LA CLASE DRAW MUESTRA EL NIVEL 2 CON SUS DETALLES
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        gameview2= new draw2(this);
        setContentView(gameview2);

        //SOLO PANTALLA VERTICAL
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    //bloquea el back button
    @Override
    public void onBackPressed() {
    }
}
