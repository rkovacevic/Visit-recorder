package com.rkovacevic;

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

public class TestEJBTest {
	
	private TestEJB testEjb = new TestEJB();
	
	@Before
	public void injectMockEntityManager() {
		EntityManager entityManager = mock(EntityManager.class);
		testEjb.entityManager = entityManager;
	}
	
	@Test
	public void testRecordVisit() {
		testEjb.recordVisit();
		
		ArgumentCaptor<Visit> visitCaptor = ArgumentCaptor.forClass(Visit.class);
		verify(testEjb.entityManager, atLeastOnce()).persist(visitCaptor.capture());
		
		long visitTimeInMillis = visitCaptor.getValue().getVisitTime().getTime();
		assertTrue("Visit time not set correctly", System.currentTimeMillis() - visitTimeInMillis < TimeUnit.SECONDS.toMillis(1));
	}

	@Test
	public void testGetVisits() {
		List<Visit> visitList = Util.createTestVisitList();
		Query mockQuery = getQueryThatReturnsList(visitList);
		when(testEjb.entityManager.createNamedQuery(anyString())).thenReturn(mockQuery);
		assertEquals(visitList, testEjb.getVisits());
		verify(testEjb.entityManager).createNamedQuery(eq(Visit.ALL));
	}

	private Query getQueryThatReturnsList(List<?> list) {
		Query mockQuery = mock(Query.class);
		when(mockQuery.getResultList()).thenReturn(list);
		return mockQuery;
	}
}
