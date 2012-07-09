/**
 * 
 */
package ch.krizi.exceptionhandler.handler.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import ch.krizi.exceptionhandler.aspect.utils.ExceptionAspectUtils;
import ch.krizi.exceptionhandler.handler.ExceptionHandlerManager;
import ch.krizi.exceptionhandler.handler.annotation.HandleException;

/**
 * Reagiert auf die Annotation {@link HandleAspect}
 * 
 * @author krizi
 * 
 */
@Aspect
public class HandleExceptionAspect {

	private static final Logger logger = LoggerFactory.getLogger(HandleExceptionAspect.class);

	@Autowired
	private ExceptionHandlerManager exceptionHandlerManager;

	@AfterThrowing(pointcut = "execution(* *(..)) && @annotation(handleException)", throwing = "exception")
	public void afterException(JoinPoint joinpoint, Throwable exception, HandleException handleException) {
		Assert.notNull(exception, "exception musst not be null");
		Assert.notNull(handleException, "annotation must not be null");

		if (logger.isDebugEnabled()) {
			logger.debug("handle Exception...");
		}

		exceptionHandlerManager.handleException((Class<?>) ExceptionAspectUtils.getTargetClass(joinpoint), exception);
	}
}
