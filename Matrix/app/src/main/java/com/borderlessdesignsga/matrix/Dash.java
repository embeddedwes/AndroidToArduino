package com.borderlessdesignsga.matrix;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Dash extends Activity {

    private boolean loopRunning = false;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        final TextView textStatus = (TextView) findViewById(R.id.threadStatus);
        textStatus.setText("Thread Stopped");

        final Button button = (Button) findViewById(R.id.square_wave_burst);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                
                //send sequence of bytes to represent data
                //probably a start byte of alternating bits
                //category byte for which part of car we will be changing
                //data byte (possibly multiple go here)
                
                //I am exploring building a class to transmit data via UART or possibly SPI or I2C (at slow speeds)
                //that way Arduino (or other uC) can easily read data coming from app using standard libraries and functions
                //and I ordered a new scope, so I can finally test my square waves and actualy get back to doing some real code once it arrives!
                
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(loopRunning)
                        {
                            //Log.d("wes","loop");
                            CarrierWave wave = new CarrierWave();
                            wave.send(0);
                            try {
                                Thread.sleep(10);
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
