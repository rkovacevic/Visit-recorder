package com.rkovacevic;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TestEJB {

	@PersistenceContext
	EntityManager entityManager;
	
	public void recordVisit() {
		Visit visit = new Visit();
		visit.setVisitTime(new Date());
		entityManager.persist(visit);
	}
	
	@SuppressWarnings("unchecked")
	public List<Visit> getVisits() {
		return entityManager.createQuery("select v from Visit v").getResultList();
	}
}
