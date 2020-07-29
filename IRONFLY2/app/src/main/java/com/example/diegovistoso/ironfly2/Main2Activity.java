package com.example.diegovistoso.ironfly2;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class Main2Activity extends AppCompatActivity {
    private draw gameview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        //LLAMA A LA CLASE DRAW MUESTRA EL NIVEL 1 CON SUS DETALLES
        gameview= new draw(this);
        setContentView(gameview);

        //SOLO PANTALLA VERTICAL
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    //bloquea el back button
    @Override
    public void onBackPressed() {
    }
    }