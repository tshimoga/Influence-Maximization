package main;

import java.util.List;

public interface InfluenceModel {
	
	
	//takes a list of influenced people and time, returns list of newly influenced people 
	public abstract List<Person> runInfluenceSimulation(List<Person> influencedPeople, int timeSteps);
}
