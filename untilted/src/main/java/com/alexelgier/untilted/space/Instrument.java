package com.alexelgier.untilted.space;

public class Instrument {

	private Point coord;
	private String name;
	private int index;
	private int root;

	public Instrument(String name, int index, int root, double xvalue, double yvalue) {
		this.coord = new Point(xvalue, yvalue);
		this.name = name;
		this.root = root;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getRoot() {
		return root;
	}

	public void setRoot(int root) {
		this.root = root;
	}

	public Point getCoordinates() {
	  return coord;
	}
}
