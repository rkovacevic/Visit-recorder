package com.rkovacevic.visitrecorder.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogProducer {

	@Produces
	public Logger getLogger(InjectionPoint ip) {
		Class<?> sourceClass = ip.getMember().getDeclaringClass();
		return LoggerFactory.getLogger(sourceClass);
	}
}
