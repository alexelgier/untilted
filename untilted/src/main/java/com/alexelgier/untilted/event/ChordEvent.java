package com.alexelgier.untilted.event;

import com.alexelgier.untilted.pitch.Chord;
import com.alexelgier.untilted.pitch.Pitch;

import java.util.stream.Stream;

public class ChordEvent extends Event {

  private Chord chord;

  public ChordEvent(Chord chord, double start, double end) {
    super(start, end);
    this.chord = chord;
  }

  public Chord getChord() {
    return chord;
  }

  public Stream<Pitch> getStream() {
    return chord.getStream();
  }
}

