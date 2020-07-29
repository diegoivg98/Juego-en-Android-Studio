package com.example.diegovistoso.ironfly2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Main5Activity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        //SOLO PANTALLA VERTICAL
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mediaPlayer = MediaPlayer.create(this, R.raw.fel);
        mediaPlayer.start();
    }

    //bloquea el back button
    @Override
    public void onBackPressed() {
    }
    //DETENER AUDIO AL CERRAR
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    public void CERRAR (View view)
    {
        finish();
    }
    //BOTON PARA VOLVER A JUGAR
    public void RETRY (View view){
        //manda al nivel 1
        Intent i = new Intent(this, Main2Activity.class );
        startActivity(i);
        //detiene la musica game over
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}