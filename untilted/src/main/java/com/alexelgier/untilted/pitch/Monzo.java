package com.alexelgier.untilted.pitch;

/**
 * Data Object for representation of prime factors.
 * @author Alex Elgier
 *
 */
public class Monzo {

  private int e2;
  private int e3;
  private int e5;
  private int e7;
  private int e11;
  private int e13;

  /**
   * Static constructor: built by difference between two monzos.
   * @param m1 First monzo.
   * @param m2 Second monzo.
   * @return A new monzo.
   */
  public static Monzo monzoByPair(Monzo m1, Monzo m2) {
    int e2 = Math.abs(m1.e2 - m2.e2);
    int e3 = Math.abs(m1.e3 - m2.e3);
    int e5 = Math.abs(m1.e5 - m2.e5);
    int e7 = Math.abs(m1.e7 - m2.e7);
    int e11 = Math.abs(m1.e11 - m2.e11);
    int e13 = Math.abs(m1.e13 - m2.e13);
    return new Monzo(e2,e3,e5,e7,e11,e13);
  }

  /**
   * Static constructor: built by prime factorization.
   * @param harmonic Number to be prime factorized.
   * @return A new monzo.
   */
  public static Monzo monzoByHarmonic(int harmonic) {
    Monzo newMonzo = new Monzo();
    int number = harmonic;
    while (number % 2 == 0) {
      newMonzo.addFactor(2);
      number /= 2;
    }
    while (number % 3 == 0) {
      newMonzo.addFactor(3);
      number /= 3;
    }
    while (number % 5 == 0) {
      newMonzo.addFactor(5);
      number /= 5;
    }
    while (number % 7 == 0) {
      newMonzo.addFactor(7);
      number /= 7;
    }
    while (number % 11 == 0) {
      newMonzo.addFactor(11);
      number /= 11;
    }
    while (number % 13 == 0) {
      newMonzo.addFactor(13);
      number /= 13;
    }
    return newMonzo;
  }

  /**
   * Static constructor: built from exponents.
   * @param e2 Exponent of 2.
   * @param e3 Exponent of 3.
   * @param e5 Exponent of 5.
   * @param e7 Exponent of 7.
   * @param e11 Exponent of 11.
   * @param e13 Exponent of 13.
   * @return A new monzo.
   */
  public static Monzo monzoByFactors(int e2, int e3, int e5, int e7, int e11, int e13) {
    return new Monzo(e2,e3,e5,e7,e11,e13);
  }

  /**
   * Default constructor.
   * Initialize to 0.
   */
  public Monzo() {
    this.e2 = 0;
    this.e3 = 0;
    this.e5 = 0;
    this.e7 = 0;
    this.e11 = 0;
    this.e13 = 0;
  }

  /**
   * Constructor with exponents.
   * @param e2 Exponent of 2.
   * @param e3 Exponent of 3.
   * @param e5 Exponent of 5.
   * @param e7 Exponent of 7.
   * @param e11 Exponent of 11.
   * @param e13 Exponent of 13.
   */
  public Monzo(int e2, int e3, int e5, int e7, int e11, int e13) {
    this.e2 = e2;
    this.e3 = e3;
    this.e5 = e5;
    this.e7 = e7;
    this.e11 = e11;
    this.e13 = e13;
  }

  /**
   * Adds one to an exponent.
   * @param prime Prime to which exponent will be added.
   */
  public void addFactor(int prime) {
    switch (prime) {
      case 2:
        this.e2 += 1;
        break;
      case 3:
        this.e3 += 1;
        break;
      case 5:
        this.e5 += 1;
        break;
      case 7:
        this.e7 += 1;
        break;
      case 11:
        this.e11 += 1;
        break;
      case 13:
        this.e13 += 1;
        break;
      default:
        break;
    }
  }

  /**
   * Adds increment to an exponent. Overload.
   * @param prime Prime to which exponent will be added.
   * @param inc Increment of exponent.
   */
  public void addFactor(int prime, int inc) {
    switch (prime) {
      case 2:
        this.e2 += inc;
        break;
      case 3:
        this.e3 += inc;
        break;
      case 5:
        this.e5 += inc;
        break;
      case 7:
        this.e7 += inc;
        break;
      case 11:
        this.e11 += inc;
        break;
      case 13:
        this.e13 += inc;
        break;
      default:
        break;
    }
  }

  /**
   * Calculates Tenney Height from monzo. (Sums log2 of prime factors)
   * @return The Tenney Height.
   */
  public double getTenneyHeight() {
    return   e2 + e3 * log2(3) + e5 * log2(5) + e7 * log2(7) + e11 * log2(11) + e13 * log2(13);
  }

  private double log2(double number) {
    return Math.log(number) / Math.log(2);
  }

}
