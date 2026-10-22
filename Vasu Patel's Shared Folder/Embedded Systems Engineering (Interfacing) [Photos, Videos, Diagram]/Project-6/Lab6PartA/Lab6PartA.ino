// Student Name: Vasukumar Patel
// Student Number: 041153940

#include <DHT11.h>

DHT11 dht11(15);

void setup() {
   
    Serial.begin(9600);

    dht11.setDelay(500); 

}

void loop() {
    
    int temperature, humidity;

    int result = dht11.readTemperatureHumidity(temperature, humidity);

    if (result == 0) {
        Serial.print("Temperature:");
        Serial.print(temperature);
        Serial.print(",Humidity:");
        Serial.println(humidity);
    } else {
        Serial.println(DHT11::getErrorString(result));
    }

}
