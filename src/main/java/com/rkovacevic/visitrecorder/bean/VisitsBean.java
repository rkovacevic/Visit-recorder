package com.rkovacevic.visitrecorder.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.rkovacevic.visitrecorder.model.Visit;

@Stateless
public class VisitsBean {

	@PersistenceContext
	EntityManager entityManager;
	
	public void recordVisit() {
		Visit visit = new Visit();
		visit.setVisitTime(new Date());
		entityManager.persist(visit);
	}
	
	@SuppressWarnings("unchecked")
	public List<Visit> getVisits() {
		return entityManager.createNamedQuery(Visit.ALL).getResultList();
	}
}
