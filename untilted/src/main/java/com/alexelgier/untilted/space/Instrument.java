package com.alexelgier.untilted.space;

public class Instrument {
	
	private int xvalue;
	private int yvalue;
	private String name;
	private int index;
	private int root;
	
	public Instrument(String name, int index, int root, int xvalue, int yvalue) {
		this.xvalue = xvalue;
		this.yvalue = yvalue;
		this.name = name;
		this.root = root;
		this.index = index;
	}

	public int getXvalue() {
		return xvalue;
	}

	public void setXvalue(int xvalue) {
		this.xvalue = xvalue;
	}

	public int getYvalue() {
		return yvalue;
	}

	public void setYvalue(int yvalue) {
		this.yvalue = yvalue;
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

}
