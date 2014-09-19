package com.borderlessdesignsga.matrix;

/**
 * Created by Wes on 9/19/2014.
 */
public class Data {

    public static final int PROTOCOL_UART = 0;
    public static final int PROTOCOL_I2C = 1;
    public static final int PROTOCOL_SPI = 2;
    public static final int PROTOCOL_CUSTOM = 3;

    public static final int SPEED_SLOW = 1200;
    public static final int SPEED_MED = 4800;
    public static final int SPEED_FAST = 9600;

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
