package com.rkovacevic.visitrecorder.model;

import org.junit.experimental.theories.DataPoint;

import com.rkovacevic.visitrecorder.model.Visit;
import com.rkovacevic.visitrecorder.test.ObjectTest;


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
