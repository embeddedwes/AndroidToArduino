AndroidToArduino
================

A communications library built to allow data to be sent from Android device to Arduino.

The intention is to make this libary have two delivery methods: bluetooth and wired connection. The bluetooth would require a bluetooth module plugged into your Arduino, the wired connection would make use of your Android device's headphone jack and communicate data to your Arduino using two data pins plus a ground connection.

My inital project I am going to be using this library for is I am trying to build an Android application to actually control certain things in my car electrically. I am going to start out and just try and control windows (so I am going to tap into electrical system of car and use Arduino to close/open relays to roll up/down the windows. And I will have an Android app with a UI that allows me to control the windows from my Android tablet. I am going to experiment and find if bluetooth or wired connection is better.
