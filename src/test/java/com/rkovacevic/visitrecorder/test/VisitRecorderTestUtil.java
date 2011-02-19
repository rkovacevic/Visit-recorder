package com.rkovacevic.visitrecorder.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rkovacevic.visitrecorder.model.Visit;

public enum VisitRecorderTestUtil {
	;

	public static List<Visit> createTestVisitList() {
		List<Visit> visitList = new ArrayList<Visit>();
		Visit visit = new Visit();
		visit.setVisitTime(new Date());
		visitList.add(visit);
		return visitList;
	}

}
