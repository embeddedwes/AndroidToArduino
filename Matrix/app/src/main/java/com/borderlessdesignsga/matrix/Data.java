package com.borderlessdesignsga.matrix;

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

    public void send(int data)
    {
        //build audio sample based off of needed data and set protocol and baud
        //will use helper functions so users can easily override this function for CUSTOM protocol

        //create AudioTrack and play sample via proper methods
        //also abstracted to helper method

        //may use specific class to house all helper methods
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
