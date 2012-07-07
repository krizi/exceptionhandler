/**
 * 
 */
package ch.krizi.exceptionhandler.suppress.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.krizi.exceptionhandler.aspect.utils.ExceptionAspectUtils;
import ch.krizi.exceptionhandler.suppress.annotation.Null;
import ch.krizi.exceptionhandler.suppress.annotation.SuppressExceptions;

/**
 * TODO create suppressException interface / use spring-context to create new
 * instances
 * 
 * @author krizi
 * 
 */
@Aspect
public class SuppressExceptionAspect {
	private static final Logger logger = LoggerFactory.getLogger(SuppressExceptionAspect.class);

	@Pointcut("execution(public * *.*(..))")
	public void anyCall() {

	}

	@Around("anyCall() && @annotation(suppressExceptions)")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint, SuppressExceptions suppressExceptions)
			throws Throwable {
		try {
			if (logger.isTraceEnabled()) {
				logger.trace("Target-Class {}, This-Class {}", proceedingJoinPoint.getTarget().getClass(),
						proceedingJoinPoint.getThis().getClass());
			}

			return proceedingJoinPoint.proceed();
		} catch (Throwable t) {
			if (!isSuppressException(t, suppressExceptions)) {
				throw t;
			} else {
				Logger clazzLogger = ExceptionAspectUtils.getLogger(proceedingJoinPoint);
				if (clazzLogger.isDebugEnabled()) {
					Object[] logParams = new Object[] { proceedingJoinPoint.getSignature().toLongString(),
							t.getClass(), t.getLocalizedMessage() };
					clazzLogger.debug("Exception suppressed: call [{}], exception [{}:Message {}] ", logParams);
				}
				if (logger.isDebugEnabled()) {
					logger.debug("Exception suppressed", t);
				}

				Class<?> returnClass = suppressExceptions.returnClass();
				if (!returnClass.equals(Null.class)) {
					return returnClass.newInstance();
				}
				return null;

			}
		}
	}

	protected boolean isSuppressException(Throwable t, SuppressExceptions suppressExceptions) {
		for (Class<? extends Throwable> clazz : suppressExceptions.value()) {
			if (ExceptionAspectUtils.isExceptionInstanceOf(t, clazz)) {
				return true;
			}
		}
		return false;
	}
}
