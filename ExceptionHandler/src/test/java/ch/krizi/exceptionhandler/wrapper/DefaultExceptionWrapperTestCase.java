/**
 * 
 */
package ch.krizi.exceptionhandler.wrapper;

import java.nio.charset.IllegalCharsetNameException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.krizi.exceptionhandler.wrapper.exception.MyOwnException;

/**
 * @author krizi
 * 
 */
public class DefaultExceptionWrapperTestCase {
	private ExceptionWrapper<Throwable, Throwable> exceptionWrapper;

	@Before
	public void preapre() {
		exceptionWrapper = new DefaultExceptionWrapper();
	}

	@Test
	public void wrapIllegalArgumentToCharsetNameException() {
		try {
			Throwable wrap = exceptionWrapper.wrap(new IllegalArgumentException(), IllegalCharsetNameException.class,
					"Exception wrapped");

			Assert.assertTrue(wrap instanceof IllegalCharsetNameException);

			IllegalCharsetNameException e = (IllegalCharsetNameException) wrap;

			Assert.assertEquals("Exception wrapped", e.getMessage());
		} catch (Throwable e) {
			Assert.fail();
		}
	}

	@Test
	public void wrapIllegalArgumentToMyOwnException() {
		try {
			Throwable wrap = exceptionWrapper.wrap(new IllegalArgumentException(), MyOwnException.class,
					"My ExceptionMessage");

			Assert.assertTrue(wrap instanceof MyOwnException);

			MyOwnException e = (MyOwnException) wrap;

			Assert.assertEquals("My ExceptionMessage", e.getMessage());
		} catch (Throwable e) {
			Assert.fail();
		}
	}
}
