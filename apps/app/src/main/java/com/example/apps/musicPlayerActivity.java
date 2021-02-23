package com.example.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class musicPlayerActivity extends AppCompatActivity {
    TextView playerPosition, playerDuration;
    SeekBar seekBar;
    ImageView btRew, btPlay, btPause, btFf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        //declarar media player
        MediaPlayer mediaPlayer;
        Handler handler= new Handler(); //permite enviar y procesar objetos
        Runnable runnable;

        //asignar variable
        playerPosition=findViewById(R.id.player_position);
        playerDuration=findViewById(R.id.player_duration);
        seekBar=findViewById(R.id.seek_bar);
        btPause=findViewById(R.id.bt_pause);
        btPlay=findViewById(R.id.bt_play);
        btRew=findViewById(R.id.bt_ew);
        btFf=findViewById(R.id.bt_ff);

        //inicializar media player
        mediaPlayer= MediaPlayer.create(this,R.raw.music); //agregar raw folder y arrastar cancion


        //iniciar el ruunable
        runnable= new Runnable() {
            @Override
            public void run() {
                //asignar el progreso al seekBar
                seekBar.setProgress(mediaPlayer.getCurrentPosition());

                handler.postDelayed(this, 500);
            }
        };

        //obtener la duracion de la reproduccion
        int duration = mediaPlayer.getDuration();

        //convertir milisegundos a minutos y segundos
        String sDuration= convertFormat(duration);

        //asginar la duracion al textview, set
        playerDuration.setText(sDuration);

        //boton play
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ocultar boton de play
                btPlay.setVisibility(View.GONE);

                //mostrar el boton de pausa
                btPause.setVisibility(View.VISIBLE);

                //iniciar media player
                mediaPlayer.start();

                //asignar el valor maximo al seekBar
                seekBar.setMax(mediaPlayer.getDuration());

                //iniciar
                handler.postDelayed(runnable,0);
            }
        });
        //boton pausa
        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ocultar el boton pausa
                btPause.setVisibility(View.GONE);

                //mostrar el boton play
                btPlay.setVisibility(View.VISIBLE);

                //pausar el media player
                mediaPlayer.pause();

                //detener el runnable
                handler.removeCallbacks(runnable);
            }
        });
        //boton Forward Fast
        btFf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtener la posicion actual del media player
                int currentPostion= mediaPlayer.getCurrentPosition();

                //obtener la duracion del media plyaer
                int duration= mediaPlayer.getDuration();

                //evaluar
                if(mediaPlayer.isPlaying() && duration!= currentPostion){
                    currentPostion=currentPostion+5000;

                    //asignar la posicion en el textView
                    playerPosition.setText(convertFormat(currentPostion));

                    //asignar la posicion actual al seekBar
                    mediaPlayer.seekTo(currentPostion);
                }
            }
        });
        //boton Rewind
        btRew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtener la posicion actual del media player
                int currentPosition=mediaPlayer.getCurrentPosition();

                //evaluar
                if(mediaPlayer.isPlaying() && currentPosition>5000){
                    currentPosition=currentPosition-5000;

                    //asignar la posicion actual al TextView
                    playerPosition.setText(convertFormat(currentPosition));

                    //asignar el prgreso al SeekBar
                    mediaPlayer.seekTo(currentPosition);
                }
            }
        });
        //seekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    //cuando se arrastre la barra
                    mediaPlayer.seekTo(progress);
                }
                //asignar la posicion actual al TextView
                playerPosition.setText(convertFormat(mediaPlayer.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //cuando finaliza la reproduccion
       mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
           @Override
           public void onCompletion(MediaPlayer mp) {
               btPause.setVisibility(View.GONE);
               btPlay.setVisibility(View.VISIBLE);

               //asignar el media player a la posicion inicial
               mediaPlayer.seekTo(0);
           }
       });

    }

    private String convertFormat(int duration) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration)-
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
        );
    }
}