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

    public boolean send(int data)
    {
        /*build audio sample based off of needed data and set protocol and baud
        will use helper functions so users can easily override this function
        for CUSTOM protocol*/

        byte bitLength = 9; //number of samples that make one bit

        byte bitCount;
        switch(protocol)
        {
            case UART:
                bitCount = 10; // 8 data bits, no parity, 1 stop bit
                break;
            case I2C:
                bitCount = 69; //pretty rough estimate, but I think this is the right bit count
                break;
            case CUSTOM:
                bitCount = 8; //not sure yet to be honest
                break;
            default:
                return false;
        }

        int bufferSize = bitLength * bitCount * 2; //must multiply by two because we have to accommodate for second channel (clock)

        /*create AudioTrack and play sample via proper methods
        also abstracted to helper method*/

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
