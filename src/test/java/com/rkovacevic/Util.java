package com.rkovacevic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public enum Util {
	;

	public static List<Visit> createTestVisitList() {
		List<Visit> visitList = new ArrayList<Visit>();
		Visit visit = new Visit();
		visit.setVisitTime(new Date());
		visitList.add(visit);
		return visitList;
	}

}
