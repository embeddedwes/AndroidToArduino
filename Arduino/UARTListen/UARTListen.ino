void setup()
{
  Serial.begin(2400);
}

void loop()
{
  if(Serial.available() > 0)
  {
    Serial.println(Serial.read(), DEC);
  }
}
