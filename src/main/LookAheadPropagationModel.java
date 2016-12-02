package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LookAheadPropagationModel implements InfluenceModel {

	private static Random random = new Random();

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
						double totalInfluence = getTotalInfluence(outgoingPerson);
						if (outgoingPerson.getThreshold() <= totalInfluence) {
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

	private static double gaussianInfluence(float mean, float variance) {
		double ret = random.nextGaussian() * mean + variance;
		if (ret < 0 || ret > 1) {
			return gaussianInfluence(mean, variance);
		} else {
			return ret;
		}
	}

	private static double getTotalInfluence(Person person) {
		double influence = 0f;
		for (Person incomingParent : person.getIncomingNeighbors()) {
			if (incomingParent.influenced()) {
				influence += gaussianInfluence(0.5f, 0.5f);
				for (Person incomingGrandParent : incomingParent
						.getIncomingNeighbors()) {
					if (incomingGrandParent.influenced()) {
						influence += gaussianInfluence(0.25f, 0.25f);
					}
				}
			}
		}
		return influence;
	}
}
