package com.alexelgier.untilted.space;

import java.util.HashMap;

public class InstrumentMap {

	protected HashMap<String, Instrument> instruments;

	public InstrumentMap() {
	  this.instruments = new HashMap<String, Instrument>();
	}

	public Centroid getCentroid() {
		return null;
	}

	public void addInstrument(String key, Instrument instrument) {
	  this.instruments.put(key, instrument);
	}

	//Spectral Centroid https://en.wikipedia.org/wiki/Spectral_centroid

}
