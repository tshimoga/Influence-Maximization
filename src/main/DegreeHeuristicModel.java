package main;

import java.util.Collections;
import java.util.List;

public class DegreeHeuristicModel{
	
	public List<Person> getInfluencialPeople(List<Person> graph,int k) {
		Collections.sort(graph,new PersonComparator());
		return graph.subList(0, k+1);
	}
}
