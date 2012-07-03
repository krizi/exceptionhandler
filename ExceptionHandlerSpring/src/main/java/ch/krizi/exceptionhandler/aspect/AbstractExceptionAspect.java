/**
 * 
 */
package ch.krizi.exceptionhandler.aspect;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An Abstract ExceptionAspect
 * 
 * @author krizi
 * 
 */
public abstract class AbstractExceptionAspect {

	/**
	 * Get Logger by JoinPoint.
	 * 
	 * @param joinpoint
	 * @return
	 */
	protected Logger getLogger(JoinPoint joinpoint) {
		return LoggerFactory.getLogger(getTargetClass(joinpoint));
	}
	
	protected Class<?> getTargetClass(JoinPoint joinpoint) {
		return joinpoint.getTarget().getClass();
	}

	/**
	 * 
	 * @param t
	 * @param exceptionInstace
	 * @return
	 */
	protected boolean isExceptionInstanceOf(Throwable t, Class<? extends Throwable> exceptionInstace) {
		return exceptionInstace.isInstance(t);
	}

}
