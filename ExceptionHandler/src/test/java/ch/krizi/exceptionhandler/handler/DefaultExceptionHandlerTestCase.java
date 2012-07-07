/**
 * 
 */
package ch.krizi.exceptionhandler.handler;

import org.junit.Test;

/**
 * @author krizi
 * 
 */
public class DefaultExceptionHandlerTestCase {
	private DefaultExceptionHandler exceptionHandler;

	@Test
	public void testWithClassAndException() {
		exceptionHandler = new DefaultExceptionHandler(this.getClass(), new Exception());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullClassAndException() {
		exceptionHandler = new DefaultExceptionHandler(null, new Exception());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithClassAndNullException() {
		exceptionHandler = new DefaultExceptionHandler(this.getClass(), null);
	}
}
