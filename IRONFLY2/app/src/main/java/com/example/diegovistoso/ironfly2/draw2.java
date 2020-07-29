package com.example.diegovistoso.ironfly2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import java.util.Timer;
import java.util.TimerTask;
import static android.graphics.BitmapFactory.decodeResource;

public class draw2 extends View {
    Bitmap mosca1;
    Bitmap dead;
    Bitmap dead2;
    private float mosca1X;
    private float mosca1Y;
    private float dead1X;
    private float dead1Y;
    private float dead2X;
    private float dead2Y;
    Bitmap mosca2;
    private Timer timer = new Timer();
    private Bitmap life[] = new Bitmap[2];
    private Paint scorep = new Paint();
    private int score;
    private Bitmap fondo2;
    private int screenWidth;
    private int screenHeight;
    private int lifecounter;
    private float mosca2X;
    private float mosca2Y;
    int mosca2Width;
    int mosca1Width;
    int deadWidth;
    int dead2Width;

    android.os.Handler handler;
    Runnable runnable;

    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer2;
    MediaPlayer hit3;

    public draw2 (Context context) {
        super(context);

        //total de vidas
        lifecounter=3;
        //sonidos
        mediaPlayer = MediaPlayer.create(context, R.raw.hit2);
        mediaPlayer2 = MediaPlayer.create(context, R.raw.egipto);
        hit3= MediaPlayer.create(context, R.raw.hit3);

        mediaPlayer2.start();
        mediaPlayer2.setLooping(true);

        //PANTALLA
        Display display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        Point size=new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        handler = new android.os.Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

        //IMAGENES
        fondo2 = decodeResource(getResources(), R.drawable.fondo2);
        mosca2 =BitmapFactory.decodeResource(getResources(), R.drawable.mosca2);
        mosca1 =BitmapFactory.decodeResource(getResources(), R.drawable.mosca1);
        dead =BitmapFactory.decodeResource(getResources(), R.drawable.dead);
        dead2 =BitmapFactory.decodeResource(getResources(), R.drawable.dead2);
        life[0]= BitmapFactory.decodeResource(getResources(), R.drawable.life);
        life[1]= BitmapFactory.decodeResource(getResources(), R.drawable.life2);

        //PUNTAJE
        scorep.setColor(Color.WHITE);
        scorep.setTextSize(40);
        scorep.setTypeface(Typeface.DEFAULT_BOLD);
        scorep.setAntiAlias(true);
        score=0;

        mosca1Width= mosca2.getWidth();
        deadWidth= dead.getWidth();
        mosca2Width= mosca2.getWidth();
        dead2Width= dead.getWidth();

        //COMIENZO DEL TIMER
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        invalidate();
                    }
                });
            }
        },0,40);
    }
    //DIBUJO EN EL CANVAS
    @Override
    protected void onDraw(Canvas canvas) {

        //DIBUJOS
        canvas.drawBitmap(fondo2, 0, 0, null);
        canvas.drawBitmap(mosca1,mosca1X,mosca1Y,null);
        canvas.drawBitmap(mosca2,mosca2X,mosca2Y,null);
        canvas.drawBitmap(dead,dead1X,dead1Y,null);
        canvas.drawBitmap(dead2,dead2X,dead2Y,null);
        canvas.drawText("SCORE: "+score, 20, 1200,scorep);

        //CORAZONES (VIDA)
        for (int i=0; i<3; i++)
        {
            int x= (int) (100 + life[0].getWidth() * 1.5 * i );
            int y=10;

            if (i < lifecounter){
                canvas.drawBitmap(life[0],x,y,null);
            }
            else{
                canvas.drawBitmap(life[1],x,y,null);
            }
        }
        //VELOCIDAD DE LA MOSCA2
        mosca1Y+=17;
        mosca2Y -= 17;
        dead1X -=16;
        dead2X +=16;

        //MOSCA1 HACIA ARRIBA
        if (mosca2Y <- mosca2Width){
            //PUNTO RANDOM
            mosca2X = (float) Math.floor(Math.random() *  (screenWidth - mosca2.getWidth()));
            mosca2Y = screenWidth + 280.0f;
        }
        //HACIA IZQUIERDA
        if (dead1X <-deadWidth){
            dead1Y= (float) Math.floor(Math.random() *  (screenWidth - dead.getWidth()));
            dead1X = screenWidth + 100.0f;
        }
        //HACIA ABAJO
        if (mosca1Y > screenHeight){
            //PUNTO RANDOM
            mosca1X = (float) Math.floor(Math.random() *  (screenWidth - mosca1.getWidth()));
            mosca1Y = screenWidth - 590.0f;
        }
        //HACIA LA DERECHA
        if (dead2X > screenWidth){
            dead2Y= (float) Math.floor(Math.random() *  (screenHeight - dead2.getHeight()));
            dead2X =- 100.0f;
        }
        handler.postDelayed(runnable, 20);
    }

    //TOCAR SOLO MOSCA1 Y SUMAR PUNTAJE
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();

        switch(action){
            case MotionEvent.ACTION_DOWN:
                //detecta el touch solo a la mosca
                if (x >= mosca1X && x < (mosca1X + mosca1.getWidth()) && y >= mosca1Y && y < (mosca1Y + mosca1.getHeight()))
                {
                    //cuando la mosca es tocada desaparece y sale en un punto random
                    mediaPlayer.start();
                    mosca1X = (float) Math.floor(Math.random() *  (screenWidth - mosca1.getWidth()));
                    mosca1Y = screenWidth - 590.0f;

                    //sonido de aplastar cuando se toca la mosca
                    mediaPlayer.start();
                    //autoincrementa score
                    score=score+1;

                    //si puntaje es igual a 25, va a la pantalla de carga
                    if (score==20){
                        Intent n2 = new Intent(getContext(), carga2ctivity.class);
                        n2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        //manda al load del nivel 2
                        getContext().startActivity(n2);
                        if(mediaPlayer2!=null){
                            //detiene la musica cuando el puntaje=25
                            mediaPlayer2.setLooping(false);
                            mediaPlayer2.stop();
                            mediaPlayer2.release();
                            mediaPlayer2 = null;
                        }
                    }
                }

                //detecta el touch solo a la mosca
                if (x >= mosca2X && x < (mosca2X + mosca2.getWidth()) && y >= mosca2Y && y < (mosca2Y + mosca2.getHeight()))
                {
                    //cuando la mosca es tocada desaparece y sale en un punto random
                    mediaPlayer.start();
                    mosca2X = (float) Math.floor(Math.random() *  (screenWidth - mosca2.getWidth()));
                    mosca2Y = screenWidth + 280.0f;

                    //sonido de aplastar cuando se toca la mosca
                    mediaPlayer.start();
                    //autoincrementa score
                    score=score+1;

                    //si puntaje es igual a 25, va a la pantalla de carga
                    if (score==20){
                        Intent n2 = new Intent(getContext(), carga2ctivity.class);
                        n2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        //manda al load del nivel 2
                        getContext().startActivity(n2);
                        if(mediaPlayer2!=null){
                            //detiene la musica cuando el puntaje=25
                            mediaPlayer2.setLooping(false);
                            mediaPlayer2.stop();
                            mediaPlayer2.release();
                            mediaPlayer2 = null;
                        }
                    }
                }
                //DETECTA TOUCH DE LA MOSCA ROJA
                if (x >= dead1X && x < (dead1X + dead.getWidth()) && y >= dead1Y && y < (dead1Y + dead.getHeight())){
                    //sonido al tocar mosca roja
                    hit3.start();
                    //punto random
                    dead1Y= (float) Math.floor(Math.random() *  (screenWidth - dead.getWidth()));
                    dead1X = screenWidth + 100.0f;
                    //CADA VEZ QUE TOCA MOSCA ROJA PIERDE UNA VIDA
                    lifecounter--;
                    if (lifecounter==0) {
                        //AL PERDER LAS 3 VIDAS, MANDA AL GAME OVER
                        Intent gameover = new Intent(getContext(), GameOverActivity.class);
                        gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        //envia puntaje al gameover
                        gameover.putExtra("score",score);
                        getContext().startActivity(gameover);
                        if(mediaPlayer2!=null){
                            //detiene la musica al acabar las 3 vidas y pasa al gameover
                            mediaPlayer2.setLooping(false);
                            mediaPlayer2.stop();
                            mediaPlayer2.release();
                            mediaPlayer2 = null;
                        }
                    }
                }
                //DETECTA TOUCH DE LA MOSCA ROJA2
                if (x >= dead2X && x < (dead2X + dead2.getWidth()) && y >= dead2Y && y < (dead2Y + dead2.getHeight())){
                    //sonido al tocar mosca roja
                    hit3.start();
                    //punto random
                    dead2Y= (float) Math.floor(Math.random() *  (screenHeight - dead2.getHeight()));
                    dead2X =- 100.0f;
                    //CADA VEZ QUE TOCA MOSCA ROJA PIERDE UNA VIDA
                    lifecounter--;
                    if (lifecounter==0) {
                        //AL PERDER LAS 3 VIDAS, MANDA AL GAME OVER
                        Intent gameover = new Intent(getContext(), GameOverActivity.class);
                        gameover.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        //envia puntaje al gameover
                        gameover.putExtra("score",score);
                        getContext().startActivity(gameover);
                        if(mediaPlayer2!=null){
                            //detiene la musica al acabar las 3 vidas y pasa al gameover
                            mediaPlayer2.setLooping(false);
                            mediaPlayer2.stop();
                            mediaPlayer2.release();
                            mediaPlayer2 = null;
                        }
                    }
                }
                break;
        }
        return true;
    }
}