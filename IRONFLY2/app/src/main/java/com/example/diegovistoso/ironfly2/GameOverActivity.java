package com.example.diegovistoso.ironfly2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    MediaPlayer gameover;
    private TextView score;
    private String punt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over2);

        //SOLO PANTALLA VERTICAL
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //COMIENZO DE AUDIO AL llegar al game over
        gameover = MediaPlayer.create(this, R.raw.gameover);
        gameover.start();

        //recoje los puntajes
        punt=getIntent().getExtras().get("score").toString();
        score = (TextView)findViewById(R.id.textView2);

        //muestra el puntaje en el gameover
        score.setText("PUNTAJE: "+ punt);

    }

    //BOTON PARA VOLVER A JUGAR
    public void RETRY (View view){
        //manda al nivel 1
        Intent i = new Intent(this, Main2Activity.class );
        startActivity(i);
        //detiene la musica game over
        if(gameover!=null){
            gameover.stop();
            gameover.release();
            gameover = null;
        }
    }
    //bloquea el back button
    @Override
    public void onBackPressed() {
    }

    //BOTON salir intro del juego
    public void CERRAR (View view)
    {
        finish();
    }

}