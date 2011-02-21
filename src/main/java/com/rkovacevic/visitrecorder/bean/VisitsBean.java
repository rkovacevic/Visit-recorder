package com.rkovacevic.visitrecorder.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;

import com.rkovacevic.visitrecorder.model.Visit;

@Named
@Stateless
public class VisitsBean {

	@Inject
	private Logger log;
	
	@PersistenceContext
	EntityManager entityManager;
	
	public void recordVisit() {
		log.info("Recording visit");
		Visit visit = new Visit();
		visit.setVisitTime(new Date());
		entityManager.persist(visit);
	}
	
	@SuppressWarnings("unchecked")
	public List<Visit> getVisits() {
		return entityManager.createNamedQuery(Visit.ALL).getResultList();
	}
}
