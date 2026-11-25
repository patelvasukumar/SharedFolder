// File purpose: Fan safety for MEGA-2 – uses the ultrasonic sensor to stop the fan and play
// an alarm melody when something gets too close (under 20 cm).

#pragma once
#include "Configuration.h"
#include "ClimateControl.h"

const uint16_t NOTE_C4 = 262, NOTE_D4 = 294, NOTE_E4 = 330, NOTE_F4 = 349;
const uint16_t NOTE_G4 = 392, NOTE_A4 = 440;

struct Note { uint16_t frequency; uint8_t beats; };
const Note SONG[] = {
  {NOTE_C4, 1}, {NOTE_C4, 1}, {NOTE_G4, 1}, {NOTE_G4, 1},
  {NOTE_A4, 1}, {NOTE_A4, 1}, {NOTE_G4, 2},
  {NOTE_F4, 1}, {NOTE_F4, 1}, {NOTE_E4, 1}, {NOTE_E4, 1},
  {NOTE_D4, 1}, {NOTE_D4, 1}, {NOTE_C4, 2},
  {0, 2}
};
const uint8_t SONG_LENGTH = sizeof(SONG) / sizeof(SONG[0]);
const unsigned long BEAT_MS = 280;

static uint8_t songIndex = 0;
static unsigned long nextNoteMs = 0;
static bool songPlaying = false;
static int lastDistanceCm = 999;

// Sends an ultrasonic pulse and returns the distance in cm (999 if nothing detected)
int readDistanceCm() {
  digitalWrite(Pins::US_TRIG, LOW);
  delayMicroseconds(3);
  digitalWrite(Pins::US_TRIG, HIGH);
  delayMicroseconds(10);
  digitalWrite(Pins::US_TRIG, LOW);
  unsigned long echoUs = pulseInLong(Pins::US_ECHO, HIGH, US_TIMEOUT_US);
  if (echoUs == 0) return 999;
  return (int)(echoUs / 58UL);
}

// Plays the next note of the alarm melody without delay() (non-blocking)
void playSongStep() {
  unsigned long now = millis();
  if (songPlaying && (long)(now - nextNoteMs) < 0) return;
  const Note& n = SONG[songIndex];
  unsigned long length = n.beats * BEAT_MS;
  if (n.frequency > 0) tone(Pins::PASSIVE_BUZZER, n.frequency, length * 85 / 100);
  else noTone(Pins::PASSIVE_BUZZER);
  nextNoteMs = now + length;
  songIndex = (songIndex + 1) % SONG_LENGTH;
  songPlaying = true;
}

// Stops the buzzer and restarts the melody from the beginning
void stopAlarm() {
  if (songPlaying) noTone(Pins::PASSIVE_BUZZER);
  songPlaying = false;
  songIndex = 0;
}

// Runs the fan when needed, but stops it and sounds the alarm if an object is too close
void updateFanControl() {

  if (!fanIsNeeded()) {
    safetyAlert = false;
    stopAlarm();
    setMotor(false);
    return;
  }

  static unsigned long lastPingMs = 0;
  if (millis() - lastPingMs >= US_PERIOD_MS) {
    lastPingMs = millis();
    lastDistanceCm = readDistanceCm();
    if (!safetyAlert && lastDistanceCm < SAFE_DISTANCE_CM) safetyAlert = true;
    else if (safetyAlert && lastDistanceCm > RESUME_DISTANCE_CM) safetyAlert = false;
  }

  if (safetyAlert) {
    setMotor(false);
    playSongStep();
  } else {
    stopAlarm();
    setMotor(true);
  }

}
