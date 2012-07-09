/**
 * 
 */
package ch.krizi.exceptionhandler.handler.factory.utils;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ch.krizi.exceptionhandler.handler.factory.AbstractExceptionHandlerFactory;

/**
 * @author krizi
 * 
 */
@Ignore
public class ExceptionHandlerFactoryUtilsDefaultTestCase {
	private ExceptionHandlerFactoryUtilsDefault exceptionHandlerFactoryUtilsDefault;

	@Before
	public void init() {
		exceptionHandlerFactoryUtilsDefault = new ExceptionHandlerFactoryUtilsDefault();
	}

	@Test
	public void testCreate() {
		List<AbstractExceptionHandlerFactory> exceptionHandlerFactories = exceptionHandlerFactoryUtilsDefault
				.getExceptionHandlerFactories();
		Assert.assertNotNull(exceptionHandlerFactories);
	}
}
