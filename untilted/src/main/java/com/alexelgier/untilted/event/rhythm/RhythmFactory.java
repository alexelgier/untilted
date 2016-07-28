package com.alexelgier.untilted.event.rhythm;

import java.util.Random;

public class RhythmFactory {

  public RhythmFactory() {

  }

  /**
   * Returns a new rhythm chosen randomly.
   * @param start Start time of rhythm.
   * @param dur Duration of rhythm.
   * @return A new Rhythm.
   */
  public Rhythm getRhythm(double start, double dur) {
    Random rand = new Random();
    int index = rand.nextInt(4);
    int s1;
    int s2;
    int e1;
    int e2;
    switch (index) {
      case 0:
        s1 = 1;
        e1 = 4;
        s2 = 4;
        e2 = 7;
        break;
      case 1:
        s1 = 2;
        e1 = 5;
        s2 = 5;
        e2 = 7;
        break;
      case 2:
        s1 = 3;
        e1 = 5;
        s2 = 5;
        e2 = 8;
        break;
      case 3:
        s1 = 3;
        e1 = 6;
        s2 = 6;
        e2 = 8;
        break;
      default:
        s1 = 0;
        e1 = 0;
        s2 = 0;
        e2 = 0;
    }
    return new Rhythm(s1,e1,s2,e2, start, dur);
  }

}
