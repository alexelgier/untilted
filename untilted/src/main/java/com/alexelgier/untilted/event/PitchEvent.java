package com.alexelgier.untilted.event;

import com.alexelgier.untilted.pitch.Pitch;

public class PitchEvent extends Event {

  private Pitch pitch;

  public PitchEvent(Pitch pitch, double start, double end) {
    super(start,end);
    this.pitch = pitch;
  }

  public Pitch getPitch() {
    return pitch;
  }
}
