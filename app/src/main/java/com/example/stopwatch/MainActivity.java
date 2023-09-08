package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running = false;
    private byte vuelta = 0;
    private int hora = 0;
    private int tiempoTotal = 0;

    private TextView vueltaText;
    private TextView horaText;
    private TextView tiempoTotalText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vueltaText = findViewById(R.id.vueltaTextView);
        horaText = findViewById(R.id.horaTextView);
        tiempoTotalText = findViewById(R.id.tiempoTotalTextView);


        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            vuelta = savedInstanceState.getByte("vuelta");
            hora = savedInstanceState.getInt("hora");
            tiempoTotal = savedInstanceState.getInt("tiempoTotal");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putByte("vuelta", vuelta);
        savedInstanceState.putInt("hora",hora);
        savedInstanceState.putInt("tiempoTotal", tiempoTotal);
    }

    public void onClickStart (View view){
        running = true;
    }

    public void onClickStop (View view){
        running = false;
    }

    public void onClickReset (View view){
        running = false;
        seconds = 0;
        vuelta = 0;
        hora = 0;
        tiempoTotal = 0;

        vueltaText.setText(getString(R.string.vuelta));
        horaText.setText(getString(R.string.hora));

        tiempoTotalText.setText(getString(R.string.tiempoTotal));
        vueltaText.setText(getString(R.string.vuelta));
        horaText.setText(getString(R.string.hora));
        tiempoTotalText.setText(getString(R.string.tiempoTotal));

    }

    private void runTimer(){
        final TextView timeView = (TextView) findViewById(R.id.time_View);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds / 3600;
                int minutes = (seconds / 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this,1000);

            }
        });
    }

    public void onClickVuelta(View view){

        running = true;
        vuelta ++;
        hora = seconds-tiempoTotal;
        tiempoTotal = seconds;

        vueltaText.append("\n"+ vuelta);
        horaText.append("\n"+ hora);
        tiempoTotalText.append("\n"+ tiempoTotal);

    }

}