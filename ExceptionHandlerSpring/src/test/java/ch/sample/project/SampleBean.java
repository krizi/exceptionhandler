package ch.sample.project;

import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;

import ch.krizi.exceptionhandler.handler.annotation.HandleException;
import ch.krizi.exceptionhandler.suppress.annotation.SuppressExceptions;
import ch.krizi.exceptionhandler.wrapper.annotation.WrapException;

@Ignore
public class SampleBean {

	@HandleException
	public void throwAndHandleException() {
		throw new IllegalArgumentException();
	}
	
	@HandleException
	@SuppressExceptions
	public void throwAndHandleExceptionAndSuppressException() {
		throw new IllegalArgumentException();
	}

	@SuppressExceptions
	public void throwAndSuppressException() {
		throw new IllegalArgumentException();
	}
	
	@SuppressExceptions
	public Object throwAndSuppressExceptionReturnNull() {
		throw new IllegalArgumentException();
	}

	@SuppressExceptions(returnClass = ArrayList.class)
	public List<?> throwAndSuppressExceptionReturnEmptyList() {
		throw new IllegalArgumentException();
	}

	public void throwWithoutSuppressException() {
		throw new IllegalArgumentException();
	}

	@SuppressExceptions(IllegalArgumentException.class)
	public void throwRuntimeExceptionSuppressIllegalArgumentException() {
		throw new RuntimeException();
	}

	@WrapException(catchException = IllegalArgumentException.class, throwAs = IllegalCharsetNameException.class)
	public void throwIllegalArgumentExceptionAndWrapToIllegalCharsetNameException() {
		throw new IllegalArgumentException();
	}

	@WrapException(catchException = IllegalCharsetNameException.class, throwAs = IllegalArgumentException.class)
	public void throwIllegalCharsetNameExceptionAndWrapToIllegalArgumentException() {
		throw new IllegalCharsetNameException("UTF-8");
	}
}
