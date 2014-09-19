package com.borderlessdesignsga.matrix;

/**
 * Created by Wes on 9/19/2014.
 */
public class Data {

    public static final int PROTOCOL_UART = 0;
    public static final int PROTOCOL_I2C = 1;
    //public static final int PROTOCOL_CUSTOM = 2;

    //The speeds below are bits per second
    public static final int SPEED_SLOW = 1200;
    public static final int SPEED_MED = 2400;
    public static final int SPEED_FAST = 4800;

    private int protocol;
    private int speed;

    public Data()
    {
        this(PROTOCOL_UART, SPEED_MED);
    }

    public Data(int protocol, int speed)
    {
        this.protocol = protocol;
        this.speed = speed;
    }

    public void send()
    {

    }

    //possibly setup a buffer and event driven read system too...

    public void read()
    {

    }

    public int getProtocol()
    {
        return protocol;
    }

    public int getSpeed()
    {
        return speed;
    }
}
