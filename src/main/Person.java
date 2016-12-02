	package main;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Person {

	private List<Person> outgoingNeighbors = new ArrayList<Person>();
	private List<Person> incomingNeighbors = new ArrayList<Person>();
	private float threshold;
	private boolean influenced;

	Person() {
		setThreshold();
	}

	public void setInfluence(boolean influence) {
		this.influenced = influence;
	}
	
	public List<Person> getOutgoingNeighbors() {
		return outgoingNeighbors;
	}
	
	public List<Person> getIncomingNeighbors() {
		return incomingNeighbors;
	}

	private void setThreshold() {
		Random r = new Random();
		threshold = r.nextFloat();
	}

	public float getThreshold() {
		return this.threshold;
	}

	public boolean influenced() {
		return this.influenced;
	}
}
