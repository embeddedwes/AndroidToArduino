package com.borderlessdesignsga.matrix;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

/**
 * Created by Wes on 9/11/2014.
 */
public class CarrierWave {
    //Use AudioTrack to create left and right channel square waves at low frequency
    //Single usage class, so it will be created, used, and destroyed per data burst sent form tablet/phone
    //clock = left channel (idle low)
    //data = right channel (idle low)

    private AudioTrack track;

    public CarrierWave() {
        byte time = 1; //seconds
        byte resolution = 2; //bytes per sample
        int sampleRate = 44100; //samples per second
        byte channels = 2; //unitless scalor
        
        int bufferSize = time * resolution * sampleRate * channels; //bytes
        
        track = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRate, AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT, bufferSize, AudioTrack.MODE_STATIC);
    }

    public void send(byte data)
    {
        //byte goes between -128 and so we need to offset data to make it between 0 and 255
        data += 128;
        
        //buffer to hold data
        short buffer[] = new short[176400];
        
        //write data here
        //possibly need to call reloadStaticData() method here
        track.write(buffer, 0, buffer.length);
        
        //play track cause we're all done here!
        track.play();
    }
}
