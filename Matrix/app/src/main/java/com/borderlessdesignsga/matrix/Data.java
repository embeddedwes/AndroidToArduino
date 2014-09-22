package com.borderlessdesignsga.matrix;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

/**
 * Created by Wes on 9/19/2014.
 */
public class Data {

    public enum Protocol {
        UART, I2C, CUSTOM
    }

    private Protocol protocol;
    private int baud;

    public Data()
    {
        this(Protocol.UART, 2400);
    }

    public Data(Protocol protocol)
    {
        this(protocol, 2400);
    }

    public Data(int baud)
    {
        this(Protocol.UART, baud);
    }

    public Data(Protocol protocol, int baud)
    {
        this.protocol = protocol;
        this.baud = (baud > 4800) ? 4800 : baud;
    }

    public boolean send(int data)
    {
        /*build audio sample based off of needed data and set protocol and baud
        will use helper functions so users can easily override this function
        for CUSTOM protocol*/

        byte bitLength = 9; //number of samples that make one bit

        byte bitCount;
        int bufferSize;
        int channels;
        AudioTrack track;

        switch(protocol)
        {
            case UART:
                bitCount = 10; // 8 data bits, no parity, 1 stop bit
                bufferSize = bitLength * bitCount;
                channels = AudioFormat.CHANNEL_OUT_MONO;
                break;
            case I2C:
                bitCount = 69; //pretty rough estimate, but I think this is the right bit count
                bufferSize = bitLength * bitCount * 2;
                channels = AudioFormat.CHANNEL_OUT_STEREO;
                break;
            case CUSTOM:
                bitCount = 8; //not sure yet to be honest
                bufferSize = bitLength * bitCount * 2;
                channels = AudioFormat.CHANNEL_OUT_STEREO;
                break;
            default:
                return false;
        }

        byte buffer[] = new byte[bufferSize];

        /*create AudioTrack and play sample via proper methods
        also abstracted to helper method*/
        try {
            track = new AudioTrack(AudioManager.STREAM_MUSIC, 44100, channels, AudioFormat.ENCODING_PCM_8BIT, bufferSize, AudioTrack.MODE_STATIC);
        } catch (IllegalArgumentException e) {
            return false;
        }

        //possibly need to call reloadStaticData() method here
        int status = track.write(buffer, 0, buffer.length);
        if(status == AudioTrack.ERROR_BAD_VALUE || status == AudioTrack.ERROR_INVALID_OPERATION) { return false; }

        try {
            //play and then immediately release the track cause we're all done here!
            track.play();
            track.release();
        } catch (IllegalArgumentException e) {
            return false;
        }

        return true;
    }

    //reading from Arduino will go here... send first priority though

    public Protocol getProtocol()
    {
        return protocol;
    }

    public int getSpeed()
    {
        return baud;
    }
}
