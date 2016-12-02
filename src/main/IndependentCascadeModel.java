package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class IndependentCascadeModel implements InfluenceModel {

	Random rand = new Random();

	@Override
	public List<Person> runInfluenceSimulation(List<Person> influencedPeople,
			int timeSteps) {
		List<Person> currentInfluenced = new ArrayList<Person>();
		Collections.shuffle(influencedPeople);
		for (int i = 0; i < timeSteps; i++) {
			for (Person influencedPerson : influencedPeople) {
				for (Person outgoingPerson : influencedPerson
						.getOutgoingNeighbors()) {
					if (!outgoingPerson.influenced()) {
						float influencdNeighborRatio = rand.nextFloat();
						if (outgoingPerson.getThreshold() <= influencdNeighborRatio) {
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
