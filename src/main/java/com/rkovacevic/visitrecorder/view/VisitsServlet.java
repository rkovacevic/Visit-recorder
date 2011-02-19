package com.rkovacevic.visitrecorder.view;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.rkovacevic.visitrecorder.bean.VisitsBean;
import com.rkovacevic.visitrecorder.model.Visit;

@WebServlet("/visit")
public class VisitsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@EJB
	VisitsBean visits;
	
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		visits.recordVisit();
		
		List<Visit> recordedVisits = visits.getVisits();
		res.getWriter().write(recordedVisits.toString());
	}

}
