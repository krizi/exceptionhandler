/**
 * 
 */
package ch.krizi.exceptionhandler.handler.factory;

import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ch.krizi.exceptionhandler.handler.DefaultExceptionHandler;
import ch.krizi.exceptionhandler.handler.ExceptionHandler;
import ch.krizi.exceptionhandler.handler.factory.utils.ExceptionHandlerUtilsDefault;

/**
 * @author krizi
 * 
 */
public class ExceptionHandlerDefaultFactoryTestCase {
	private ExceptionHandlerDefaultFactory factory;

	@Before
	public void prepareFactory() {
		factory = new ExceptionHandlerDefaultFactory();
		ExceptionHandlerUtilsDefault exceptionHandlerUtils = new ExceptionHandlerUtilsDefault();
		factory.setExceptionHandlerUtils(exceptionHandlerUtils);
		HashMap<String, Class<? extends ExceptionHandler<?>>> mapping = new HashMap<String, Class<? extends ExceptionHandler<?>>>();
		mapping.put("someBeanId", DefaultExceptionHandler.class);
		factory.setMapping(mapping);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithoutExceptionFactoryUtils() {
		factory.setExceptionHandlerUtils(null);
		factory.getExceptionHandler(this.getClass(), new Exception());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMappingNullFactory() {
		factory.setMapping(null);
		factory.getExceptionHandler(this.getClass(), new Exception());
	}

	@Test
	public void testMappingEmptyFactory() {
		factory.setMapping(new HashMap<String, Class<? extends ExceptionHandler<?>>>());
		List<ExceptionHandler<?>> exceptionHandler = factory.getExceptionHandler(this.getClass(), new Exception());

		Assert.assertNotNull(exceptionHandler);
		Assert.assertTrue(exceptionHandler.isEmpty());
	}
	
	@Test
	public void testFactory() {
		List<ExceptionHandler<?>> exceptionHandler = factory.getExceptionHandler(this.getClass(), new Exception());
		
		Assert.assertNotNull(exceptionHandler);
		Assert.assertEquals(1, exceptionHandler.size());
		Assert.assertEquals(DefaultExceptionHandler.class, exceptionHandler.get(0).getClass());
	}
}
