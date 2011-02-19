package com.rkovacevic;

import org.junit.experimental.theories.DataPoint;


public class VisitTest extends ObjectTest {
	
	@DataPoint
	public static Visit blank = new Visit();

	@DataPoint
	public static Visit withId = createVisitWithId(Long.valueOf(1));

	@DataPoint
	public static Visit withDifferentId = createVisitWithId(Long.valueOf(2));
	
	@DataPoint
	public static Visit nullVisit = null;
	
	@DataPoint
	public static Object otherObject = new String("TEST");
	
	private static Visit createVisitWithId(Long id) {
		Visit visit = new Visit();
		visit.setId(id);
		return visit;
	}
}
