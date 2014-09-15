int clock = 0; //interrupt 0 = pin 2
int data = 3; //pin 3

/*
On every state change of the clock pin (HIGH to LOW and LOW to HIGH) the digital data pin is read
this data is pushed bit by bit into a byte and then sent across to the serial port when ready (all 8 bits have arrived)
By virute of transfer method, the clock and data lines are 90 degress out of phase with one another, so when clock toggles
the data pulse is half way through the logic level for the current bit
*/

volatile byte buffer = 0;
volatile byte head = 0;
volatile boolean dataReady = false;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  
  pinMode(data, INPUT);
  attachInterrupt(clock, data_available, CHANGE);
}

void loop() {
  // put your main code here, to run repeatedly: 
  if(dataReady == true)
  {
    Serial.println(buffer);
    buffer = 0;
    dataReady = false;
  }
}

void data_available()
{
  if(digitalRead(data) == HIGH) bitSet(buffer,head);
  head++;
  if(head == 8)
  {
    dataReady = true;
    head = 0;
  }
  delay(10); //sketch debouncing!!
}
