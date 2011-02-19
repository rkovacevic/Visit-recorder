package com.rkovacevic;

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

public class TestServletTest {

	private TestServlet testServlet = new TestServlet();
	
	private ServletRequest request = mock(ServletRequest.class);
	private ServletResponse response = mock(ServletResponse.class);
	
	private static final List<Visit> visitList = Util.createTestVisitList();
	
	@Before
	public void injectEjbAndInvokeServlet() throws IOException, ServletException {
		injectEjb();
		invokeServlet();
	}

	private void injectEjb() {
		TestEJB testEjb = mock(TestEJB.class);
		testServlet.testEjb = testEjb;
		when(testServlet.testEjb.getVisits()).thenReturn(visitList);
	}
	
	private void invokeServlet() throws IOException, ServletException {
		PrintWriter mockPrintWriter = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(mockPrintWriter);
		testServlet.service(request, response);
	}
	
	@Test
	public void verifyThatVisitListIsPrinted() throws IOException {
		ArgumentCaptor<String> writerCaptor = ArgumentCaptor.forClass(String.class);
		verify(response.getWriter()).write(writerCaptor.capture());
		assertEquals(visitList.toString(), writerCaptor.getValue());
	}
	
	@Test
	public void verifyThatRecordVisitIsInvokedOnce() {
		verify(testServlet.testEjb, times(1)).recordVisit();
	}
}
