/**
 * 
 */
package ch.krizi.exceptionhandler.wrapper.aspect;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.krizi.exceptionhandler.aspect.utils.ExceptionAspectUtils;
import ch.krizi.exceptionhandler.wrapper.ExceptionWrapper;
import ch.krizi.exceptionhandler.wrapper.annotation.WrapException;
import ch.krizi.exceptionhandler.wrapper.annotation.WrapExceptions;

/**
 * @author krizi
 * 
 */
@Aspect
public class WrapExceptionAspect {
	private static final Logger logger = LoggerFactory.getLogger(WrapExceptionAspect.class);

	@Pointcut("execution(public * *.*(..))")
	public void anyCall() {

	}

	@Around("anyCall() && @annotation(wrapException)")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint, WrapException wrapException) throws Throwable {
		try {
			return proceedingJoinPoint.proceed();
		} catch (Throwable throwable) {
			if (logger.isTraceEnabled()) {
				logger.trace("catch {}", throwable.getClass());
			}
			wrapAndThrowException(throwable, wrapException);
		}
		return null;
	}

	@Around("anyCall() && @annotation(wrapExceptions)")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint, WrapExceptions wrapExceptions) throws Throwable {
		try {
			return proceedingJoinPoint.proceed();
		} catch (Throwable throwable) {
			wrapAndThrowException(throwable, wrapExceptions.value());
		}
		return null;
	}

	protected void wrapAndThrowException(Throwable throwable, WrapException... wrapExceptions) throws Throwable {
		Throwable wrappedException = null;
		try {
			wrappedException = wrapException(throwable, wrapExceptions);
		} catch (Throwable e) {
			if (logger.isErrorEnabled()) {
				logger.error("error while wrapping exception", e);
			}
		}
		if (wrappedException != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("throw catched exception [{}] as new wrapped exception [{}]", throwable.getClass(),
						wrappedException.getClass());
			}
			throw wrappedException;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("can not wrap exception - throw root-exception");
		}
		throw throwable;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Throwable wrapException(Throwable throwable, WrapException... wrapExceptions) throws Throwable {
		if (!ArrayUtils.isEmpty(wrapExceptions)) {
			for (WrapException wrapException : wrapExceptions) {
				if (ExceptionAspectUtils.isExceptionInstanceOf(throwable, wrapException.catchException())) {
					Class<? extends ExceptionWrapper<?, ?>> exceptionWrapperClass = wrapException.using();
					ExceptionWrapper newInstance = exceptionWrapperClass.newInstance();
					return newInstance.wrap(throwable, wrapException.throwAs(), wrapException.message());
				}
			}
		}
		return null;
	}
}
