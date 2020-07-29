package com.example.diegovistoso.ironfly2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    private Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Si la versión android es menor que Jellybean, usa este llamado para esconder la barra de estatus.
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        //SOLO PANTALLA VERTICAL
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    //COMIENZO DE AUDIO AL ABRIR EL JUEGO
    mediaPlayer = MediaPlayer.create(this, R.raw.intro);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialog();
            }
        });

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
    //BOTON salir intro del juego
    public void CERRAR (View view)
    {
        finish();
    }
    //BOTON JUGAR
    public void PLAY (View view){
        Intent i = new Intent(this, Main2Activity.class );
        startActivity(i);
        //detiene la musica intro
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    //se muestran las instrucciones del juego
    public void opendialog (){
        //llama a la clase del alert dialog
        dialog dial = new dialog();
        dial.show(getSupportFragmentManager(),"dialog");
    }

}