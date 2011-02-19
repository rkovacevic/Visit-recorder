package com.rkovacevic;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@EJB
	TestEJB testEjb;
	
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		testEjb.recordVisit();
		
		List<Visit> recordedVisits = testEjb.getVisits();
		res.getWriter().write(recordedVisits.toString());
	}

}
