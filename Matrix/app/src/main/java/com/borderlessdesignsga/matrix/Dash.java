package com.borderlessdesignsga.matrix;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class Dash extends Activity {

    private boolean loopRunning = false;
    private Thread thread;
    private Data data;
    byte bitLength = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        data = new Data();

        final TextView bitLengthStatus = (TextView) findViewById(R.id.bitLengthStatus);
        bitLengthStatus.setText("" + 9);

        final SeekBar bitLengthControl = (SeekBar) findViewById(R.id.bitLengthController);

        bitLengthControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                //do something here with data
                bitLength = (byte)(progressChanged + 1);
                bitLengthStatus.setText("" +  (progressChanged + 1));
            }
        });

        final TextView textStatus = (TextView) findViewById(R.id.threadStatus);
        textStatus.setText("Thread Stopped");

        final Button buttonPeriodic = (Button) findViewById(R.id.square_wave_periodic);
        buttonPeriodic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Data data;
                        data = new Data();
                        while(loopRunning)
                        {
                            data.send(170, bitLength);
                            try {
                                Thread.sleep(10); //10 milliseconds
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                if(loopRunning == false) {
                    loopRunning = true;
                    textStatus.setText("Thread Running");
                    thread.start();
                }
                else
                {
                    loopRunning = false;
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    textStatus.setText("Thread Stopped");
                }
            }
        });

        final Button buttonBurst = (Button) findViewById(R.id.square_wave_burst);
        buttonBurst.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data.send(170, bitLength);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
