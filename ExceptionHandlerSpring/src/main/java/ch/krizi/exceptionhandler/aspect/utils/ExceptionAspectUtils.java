/**
 * 
 */
package ch.krizi.exceptionhandler.aspect.utils;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author krizi
 * 
 */
public class ExceptionAspectUtils {
	/**
	 * get Logger for JoinPoint.
	 * 
	 * @param joinpoint
	 * @return
	 */
	public static Logger getLogger(JoinPoint joinpoint) {
		return LoggerFactory.getLogger(getTargetClass(joinpoint));
	}

	/**
	 * get class for joinpoint
	 * 
	 * @param joinpoint
	 * @return
	 */
	public static Class<?> getTargetClass(JoinPoint joinpoint) {
		return joinpoint.getTarget().getClass();
	}

	/**
	 * 
	 * @param t
	 * @param exceptionInstace
	 * @return
	 */
	public static boolean isExceptionInstanceOf(Throwable t, Class<? extends Throwable> exceptionInstace) {
		return exceptionInstace.isInstance(t);
	}
}
