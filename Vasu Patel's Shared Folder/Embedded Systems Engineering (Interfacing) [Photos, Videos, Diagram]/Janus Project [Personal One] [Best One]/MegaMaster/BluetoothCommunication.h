// File purpose: Bluetooth (HC-08) communication for MEGA-1 – starts the link and reads
// commands from the phone, passing them to the shared command parser.

#pragma once
#include "Configuration.h"

// Starts the HC-08 Bluetooth serial at 9600 baud and sends a ready message to the phone
void setupBluetooth() {

#if !BLUETOOTH_ON_SERIAL0
  bluetooth.begin(BT_BAUD);
  bluetooth.listen();
#endif
  
  bluetooth.print(F("MEGA-1 ready. Send HELP for commands.\r\n"));

}

// Reads characters from the phone and runs the command when a full line is received
void pollBluetooth() {

#if BLUETOOTH_ON_SERIAL0
  return;
#endif
 
  static char line[40];
  static uint8_t len = 0;

  while (bluetooth.available()) {

    char c = (char)bluetooth.read();

    if (c == '\n' || c == '\r') {
    
      if (len > 0) { line[len] = '\0'; executeCommand(line, FROM_BLUETOOTH); len = 0; }
    
    } else if (len < sizeof(line) - 1) {
    
      line[len++] = c;
    
    }

  }

}
