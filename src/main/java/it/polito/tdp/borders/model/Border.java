package it.polito.tdp.borders.model;

public class Border {
	
	private int state1;
	private int state2;
	private int year;
	private Country c1;
	private Country c2;
	
	public Border(int state1, int state2, int year) {
		super();
		this.state1 = state1;
		this.state2 = state2;
		this.year = year;
	}
	
	public int getState1() {
		return state1;
	}
	public void setState1(int state1) {
		this.state1 = state1;
	}
	public int getState2() {
		return state2;
	}
	public void setState2(int state2) {
		this.state2 = state2;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + state1;
		result = prime * result + state2;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		if (state1 != other.state1)
			return false;
		if (state2 != other.state2)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return state1 + " - " + state2 + ", " + year;
	}

	public Country getC1() {
		return c1;
	}

	public void setC1(Country c1) {
		this.c1 = c1;
	}

	public Country getC2() {
		return c2;
	}

	public void setC2(Country c2) {
		this.c2 = c2;
	}
	
	

}
