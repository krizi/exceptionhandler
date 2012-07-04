/**
 * 
 */
package ch.krizi.exceptionhandler.handler.factory;

import java.util.ArrayList;
import java.util.List;

import ch.krizi.exceptionhandler.handler.CommonExceptionHandler;
import ch.krizi.exceptionhandler.handler.ExceptionHandler;

/**
 * @author krizi
 * 
 */
public class ExceptionHandlerFactory extends AbstractExceptionHandlerFactory {

	@Override
	public List<ExceptionHandler<?>> getExceptionHandler(Class<?> clazz,
			Throwable exception) {
		List<ExceptionHandler<?>> handler = new ArrayList<ExceptionHandler<?>>();

		handler.add(new CommonExceptionHandler(clazz, exception));

		return handler;
	}

	@Override
	public boolean hasSupportedExceptionHandler(Throwable exception) {
		// TODO Auto-generated method stub
		return false;
	}

}
