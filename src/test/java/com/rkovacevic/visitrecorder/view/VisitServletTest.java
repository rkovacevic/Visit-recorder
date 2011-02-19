package com.rkovacevic.visitrecorder.view;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.rkovacevic.visitrecorder.bean.VisitsBean;
import com.rkovacevic.visitrecorder.model.Visit;
import com.rkovacevic.visitrecorder.test.VisitRecorderTestUtil;
import com.rkovacevic.visitrecorder.view.VisitsServlet;

public class VisitServletTest {

	private VisitsServlet visitsServlet = new VisitsServlet();
	
	private ServletRequest request = mock(ServletRequest.class);
	private ServletResponse response = mock(ServletResponse.class);
	
	private static final List<Visit> visitList = VisitRecorderTestUtil.createTestVisitList();
	
	@Before
	public void injectEjbAndInvokeServlet() throws IOException, ServletException {
		injectEjb();
		invokeServlet();
	}

	private void injectEjb() {
		VisitsBean visitsBean = mock(VisitsBean.class);
		visitsServlet.visits = visitsBean;
		when(visitsServlet.visits.getVisits()).thenReturn(visitList);
	}
	
	private void invokeServlet() throws IOException, ServletException {
		PrintWriter mockPrintWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(mockPrintWriter);
		visitsServlet.service(request, response);
	}
	
	@Test
	public void verifyThatVisitListIsPrinted() throws IOException {
		ArgumentCaptor<String> writerCaptor = ArgumentCaptor.forClass(String.class);
		verify(response.getWriter()).write(writerCaptor.capture());
		assertEquals(visitList.toString(), writerCaptor.getValue());
	}
	
	@Test
	public void verifyThatRecordVisitIsInvokedOnce() {
		verify(visitsServlet.visits, times(1)).recordVisit();
	}
}
