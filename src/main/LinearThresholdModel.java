package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LinearThresholdModel implements InfluenceModel{

	@Override
	public List<Person> runInfluenceSimulation(List<Person> influencedPeople,
			int timeSteps) {
		List<Person> currentInfluenced = new ArrayList<Person>();
		Collections.shuffle(influencedPeople);
		for(int i=0;i<timeSteps;i++) {
			for(Person influencedPerson:influencedPeople) {
				for(Person outgoingPerson:influencedPerson.getOutgoingNeighbors()) {
					if(!outgoingPerson.influenced()) {
						int influencedNeighborCount=0;
						int incomingNeighbors=0;
						for(Person incomingPerson:outgoingPerson.getIncomingNeighbors())
						{
							if(incomingPerson.influenced()) {
								influencedNeighborCount++;
							}
							incomingNeighbors++;
						}
						float influencdNeighborRatio = (float)influencedNeighborCount/incomingNeighbors;
						if(outgoingPerson.getThreshold()<=influencdNeighborRatio) {
							outgoingPerson.setInfluence(true);
							currentInfluenced.add(outgoingPerson);
							influencedPeople.add(outgoingPerson);
						}
					}
				}
			}
		}
		return currentInfluenced;
	}
}