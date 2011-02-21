package com.rkovacevic.visitrecorder.bean;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.rkovacevic.visitrecorder.bean.VisitsBean;
import com.rkovacevic.visitrecorder.model.Visit;
import com.rkovacevic.visitrecorder.test.VisitRecorderTestUtil;

public class VisitsBeanTest {
	
	private VisitsBean visitsBean = new VisitsBean();
	
	@Before
	public void injectMockEntityManager() {
		EntityManager entityManager = mock(EntityManager.class);
		visitsBean.entityManager = entityManager;
	}
	
	@Test
	public void testRecordVisit() {
		visitsBean.recordVisit();
		
		ArgumentCaptor<Visit> persistedVisit = ArgumentCaptor.forClass(Visit.class);
		verify(visitsBean.entityManager, atLeastOnce())
		.persist(persistedVisit.capture());
		
		long visitTimeInMillis = persistedVisit.getValue().getVisitTime().getTime();
		assertTrue("Visit time not set correctly", 
				System.currentTimeMillis() - visitTimeInMillis < TimeUnit.SECONDS.toMillis(1));
	}

	@Test
	public void testGetVisits() {
		List<Visit> visitList = VisitRecorderTestUtil.createTestVisitList();
		Query mockQuery = getQueryThatReturnsList(visitList);
		when(visitsBean.entityManager.createNamedQuery(anyString()))
		.thenReturn(mockQuery);
		assertEquals(visitList, visitsBean.getVisits());
		verify(visitsBean.entityManager).createNamedQuery(eq(Visit.ALL));
	}

	private Query getQueryThatReturnsList(List<?> list) {
		Query mockQuery = mock(Query.class);
		when(mockQuery.getResultList()).thenReturn(list);
		return mockQuery;
	}
}
