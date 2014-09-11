package com.borderlessdesignsga.matrix;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

/**
 * Created by Wes on 9/11/2014.
 */
public class CarrierWave {
    //Use AudioTrack to create left and right channel square waves at low frequency
    //clock = left channel (idle low)
    //data = right channel (idle low)

    AudioTrack track;

    public CarrierWave() {
        int sampleFrequency = 44100;
        int bufferSize = AudioTrack.getMinBufferSize(sampleFrequency, AudioFormat.CHANNEL_OUT_STEREO,AudioFormat.ENCODING_PCM_16BIT);
        track = new AudioTrack(AudioManager.STREAM_MUSIC, sampleFrequency, AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT, bufferSize, AudioTrack.MODE_STATIC);
    }


}
