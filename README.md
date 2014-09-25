AndroidToArduino
================

A communications library built to allow data to be sent from Android device to Arduino. I want communication to be two ways, so that data can also be sent to the Android device from the Arduino as well as data being able to be sent out from the Android device.

The intention is to make this libary have two main delivery methods: bluetooth and wired connection. The bluetooth would require a bluetooth module plugged into your Arduino, the wired connection would make use of your Android device's headphone jack and communicate data to your Arduino using two data pins plus a ground connection (plus microphone connection for data receiving).

My inital project I am going to be using this library for is I am trying to build an Android application to actually control certain things in my car electrically. I am going to start out and just try and control windows (so I am going to tap into electrical system of car and use Arduino to close/open relays to roll up/down the windows. And I will have an Android app with a UI that allows me to control the windows from my Android tablet. I am going to experiment and find if bluetooth or wired connection is better. But the library will support both.

I am thinking bluetooth will be better in the end since it is likely faster, more reliable, easier to setup, and more portable. However I like the concept of sending/receiving data via the audio jack so that is why I am exploring that option as there may be applications where other can use the headphone jack but not the bluetooth.
