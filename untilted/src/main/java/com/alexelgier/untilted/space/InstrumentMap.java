package com.alexelgier.untilted.space;

import java.util.HashMap;
import java.util.Map;

public class InstrumentMap {

	protected HashMap<String, Instrument> instruments;
	protected Centroid centroid;

	public InstrumentMap() {
	  this.instruments = new HashMap<String, Instrument>();
	  this.centroid = null;
	}

	public InstrumentMap(Map<String, Instrument> map) {
	  this.instruments = new HashMap<String, Instrument>();
	  this.instruments.putAll(map);
	  this.centroid = getCentroid();
	}

	/** Getter for the Centroid. Uses lazy-initialization.
	 * @return The Centroid.
	 */
	public Centroid getCentroid() {
	  if (this.centroid == null) {
	    int count = 0;
	    double xvalues = 0;
	    double yvalues = 0;
	    //Centroid position: Sum all coordinates
	    for (Instrument instrument: instruments.values()) {
	      xvalues += instrument.getCoordinates().xvalue;
	      yvalues += instrument.getCoordinates().yvalue;
	      count++;
	    } //and get avg.
	    double centroidX = xvalues / count;
	    double centroidY = yvalues / count;
	    //Centroid absolute deviance and range.
	    //Deviance: Sum magnitude of distance from centroid for all coordinates
	    //Range: Difference between Max-Min distance.
	    count = 0;
	    double omegaSum = 0;
	    double maxOmega = 0;
	    double minOmega = 999999999;
	    double omega;
	    for (Instrument instrument: instruments.values()) {
	      double xomega = Math.abs(instrument.getCoordinates().getX() - centroidX);
	      double yomega = Math.abs(instrument.getCoordinates().getY() - centroidY);
	      omega = Math.sqrt((Math.pow(xomega, 2) + Math.pow(yomega, 2)));
	      maxOmega = Math.max(maxOmega, omega);
	      minOmega = Math.min(minOmega, omega);
	      omegaSum += omega;
	      count++;
	    } //and get avg.
	    double deviance = omegaSum / count;
	    double range = Math.abs(maxOmega-minOmega);
	    this.centroid = new Centroid(centroidX, centroidY, deviance, range);
	  }
	  return this.centroid;
	}

	//Spectral Centroid https://en.wikipedia.org/wiki/Spectral_centroid

}
