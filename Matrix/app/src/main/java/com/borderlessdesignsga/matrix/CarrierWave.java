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

    public CarrierWave()
    {
        //frequency of square wave will be equal to (1 / time) * 8
        //1.00 seconds = 8 Hz
        //0.10 seconds = 80 Hz
        //0.01 seconds = 800 Hz
        //not sure what max frequency is (to be tested later), but from past experientation using high frequencies resulted in the wave corners start to get rounded off
        //at a high enough frequency the sqare wave looks more like a sine wave then a square wave
        //this is due to filtering hardware inside tablet/phone since no audio jack cirucitry is built to output smooth sinusoidal waves not harsh square waves
        //may be device dependent, so may need tweaking, but we will start will lower frequences to make it as compatible as possible
        //I am hoping to attain 800 Hz for standard usage cause 8 Hz will be way to slow to be useful
        
        byte time = 1; //seconds (time used up to send 8 bits of data)
        byte resolution = 1; //bytes per sample (1 byte = 8 bits, 2 bytes = 16 bits)
        int sampleRate = 44100; //samples per second
        byte channels = 2; //unitless scalor
        
        int bufferSize = time * resolution * sampleRate * channels; //bytes
        
        track = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRate, AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_8BIT, bufferSize, AudioTrack.MODE_STATIC);
    }

    public void send(byte data)
    {
        //byte goes between -128 and 127 so we need to offset data to make it between 0 and 255
        data += 128;
        
        //buffer to hold data
        byte buffer[] = new byte[88200];
        
        //note we will only be using half of the available range since we don't want to put negative voltage into Arduino pins
        //so out range is really from 0 to 127 (so 7 bits of accuracy, which should be plenty for a digital system)
        
        //with this code here both channels should be identical, and the output voltage should toggle between 0V and Vmax (dependant on device volume that is)
        //we are just making it so over the span of 1 second a square wave will be generated that toggles 8 times
        //this is a frequency of 8Hz, which of course can be sped up considerable to improve data throughput, but we'll keep it slow for testing purposes
        for(byte i = 0; i < 8; i++)
        {
            int length = 88200 / 8;
            int start = i * length;
            
            if(i % 2 == 0) //even
                Arrays.fill(buffer, start, start + length, 0);
            else //odd
                Arrays.fill(buffer, start, start + length, 127);
        }
        
        /*
        
        byte left[] = new byte[44100];
        
        //setup clock - should be 90 degrees out of phase with data line
        for(byte i = 0; i < 8; i++)
        {
            int length = 88200 / 8;
            int start = i * length - (length / 2);
            
            if(i == 0)
                Arrays.fill(left, start, start + (length / 2), 0);
            else if(i % 2 == 0) //even
                Arrays.fill(left, start, start + length, 0);
            else //odd
                Arrays.fill(left, start, start + length, 127);
        }
        
        byte right[] = new byte[44100];
        
        //should equal 85 decimal if clocked correctly into Arduino
        for(byte i = 0; i < 8; i++)
        {
            int length = 88200 / 8;
            int start = i * length;
            
            if(i % 2 == 0) //even
                Arrays.fill(right, start, start + length, 0);
            else //odd
                Arrays.fill(right, start, start + length, 127);
        }
        
        //interleave left and right channels into buffer array
        for(int i = 0; i < left.length; i++)
        {
            buffer[i * 2] = left[i];
            buffer[(i * 2) + 1] = right[i];
        }
        
        */
        
        //write data here
        //possibly need to call reloadStaticData() method here
        track.write(buffer, 0, buffer.length);
        
        //play track cause we're all done here!
        track.play();
    }
}
