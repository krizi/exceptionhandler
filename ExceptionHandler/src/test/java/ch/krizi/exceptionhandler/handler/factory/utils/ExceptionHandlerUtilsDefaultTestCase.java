/**
 * 
 */
package ch.krizi.exceptionhandler.handler.factory.utils;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ch.krizi.exceptionhandler.handler.DefaultExceptionHandler;
import ch.krizi.exceptionhandler.handler.ExceptionHandler;

/**
 * @author krizi
 * 
 */
public class ExceptionHandlerUtilsDefaultTestCase {
	private ExceptionHandlerUtilsDefault exceptionHandlerUtilsDefault;

	@Before
	public void init() {
		exceptionHandlerUtilsDefault = new ExceptionHandlerUtilsDefault();
	}

	@Test
	public void testCreateValidExceptionHandler() {
		ExceptionHandler<?> exceptionHandler = exceptionHandlerUtilsDefault.createExceptionHandler(getClass(),
				new Exception(), "someId", DefaultExceptionHandler.class);

		Assert.assertNotNull(exceptionHandler);
		Assert.assertTrue(exceptionHandler instanceof DefaultExceptionHandler);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateInvalidExceptionHandlerWithoutClass() {
		exceptionHandlerUtilsDefault.createExceptionHandler(getClass(), new Exception(), "someId", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateInvalidExceptionHandlerWithoutException() {
		exceptionHandlerUtilsDefault.createExceptionHandler(getClass(), null, "someId", DefaultExceptionHandler.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateInvalidExceptionHandlerWithoutThrowingClass() {
		exceptionHandlerUtilsDefault.createExceptionHandler(null, new Exception(), "someId",
				DefaultExceptionHandler.class);
	}

	@Test
	public void testCreateInvalidExceptionHandlerWithoutBeanId() {
		ExceptionHandler<?> exceptionHandler = exceptionHandlerUtilsDefault.createExceptionHandler(getClass(),
				new Exception(), null, DefaultExceptionHandler.class);

		Assert.assertNotNull(exceptionHandler);
		Assert.assertTrue(exceptionHandler instanceof DefaultExceptionHandler);
	}
}
