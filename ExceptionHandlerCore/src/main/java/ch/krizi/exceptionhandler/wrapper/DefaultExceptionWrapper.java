/**
 * 
 */
package ch.krizi.exceptionhandler.wrapper;

import java.lang.reflect.Constructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The defaullt ExceptionWrapper. Try to create new Exception with following Constructors:
 * <ul>
 * <li>String, Throwable</li>
 * <li>Throwable</li>
 * <li>String</li>
 * <li>(without params)</li>
 * </ul>
 * 
 * @author krizi
 * 
 */
public class DefaultExceptionWrapper implements ExceptionWrapper<Throwable, Throwable> {

	private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionWrapper.class);

	public Throwable wrap(Throwable throwedException, Class<? extends Throwable> wrapTo, String message)
			throws Throwable {
		Throwable newInstance = null;
		try {
			newInstance = createException(wrapTo, message, throwedException);
		} catch (NoSuchMethodException e1) {
			if (logger.isErrorEnabled()) {
				logger.error("constructor not found. LocalizedMessage [{}]", e1.getLocalizedMessage());
			}
			try {
				newInstance = createException(wrapTo, throwedException);
			} catch (NoSuchMethodException e2) {
				if (logger.isErrorEnabled()) {
					logger.error("constructor not found. LocalizedMessage [{}]", e2.getLocalizedMessage());
				}
				try {
					newInstance = createException(wrapTo, message);
				} catch (NoSuchMethodException e3) {
					if (logger.isErrorEnabled()) {
						logger.error("constructor not found. LocalizedMessage [{}]", e3.getLocalizedMessage());
					}
					try {
						newInstance = createException(wrapTo);
					} catch (NoSuchMethodException e4) {
						if (logger.isErrorEnabled()) {
							logger.error("constructor not found. LocalizedMessage [{}]", e4.getLocalizedMessage());
						}
					}
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("root-Exception [{}] new wrapped exception [{}]", throwedException, newInstance.getClass());
		}
		if (newInstance == null) {
			throw new WrapperException("no constructor found");
		}

		return newInstance;
	}

	/**
	 * Create new instance using constructor [String, Throwable].
	 * 
	 * @param wrapTo
	 * @param message
	 * @param throwable
	 * @return
	 * @throws Throwable
	 */
	protected Throwable createException(Class<? extends Throwable> wrapTo, String message, Throwable throwable)
			throws Throwable {
		Constructor<? extends Throwable> constructor = null;
		constructor = wrapTo.getConstructor(String.class, Throwable.class);
		Throwable newInstance = constructor.newInstance(message, throwable);
		if (logger.isDebugEnabled()) {
			logger.debug("use constructor(String, Throwable) to create new Exception.");
		}
		return newInstance;
	}

	/**
	 * Create new instance using constructor [Throwable].
	 * 
	 * @param wrapTo
	 * @param throwable
	 * @return
	 * @throws Throwable
	 */
	protected Throwable createException(Class<? extends Throwable> wrapTo, Throwable throwable) throws Throwable {
		Constructor<? extends Throwable> constructor = null;
		constructor = wrapTo.getConstructor(Throwable.class);
		Throwable newInstance = constructor.newInstance(throwable);
		if (logger.isDebugEnabled()) {
			logger.debug("use constructor(Throwable) to create new Exception.");
		}
		return newInstance;
	}

	/**
	 * Create new instance using constructor [String].
	 * 
	 * @param wrapTo
	 * @param message
	 * @return
	 * @throws Throwable
	 */
	protected Throwable createException(Class<? extends Throwable> wrapTo, String message) throws Throwable {
		Constructor<? extends Throwable> constructor = null;
		constructor = wrapTo.getConstructor(String.class);
		Throwable newInstance = constructor.newInstance(message);
		if (logger.isDebugEnabled()) {
			logger.debug("use constructor(String) to create new Exception.");
		}
		return newInstance;
	}

	/**
	 * Create new instance using constructor without parameter.
	 * 
	 * @param wrapTo
	 * @return
	 * @throws Throwable
	 */
	protected Throwable createException(Class<? extends Throwable> wrapTo) throws Throwable {
		Constructor<? extends Throwable> constructor = null;
		constructor = wrapTo.getConstructor();
		Throwable newInstance = constructor.newInstance();
		if (logger.isDebugEnabled()) {
			logger.debug("use constructor without parameter to create new Exception.");
		}
		return newInstance;
	}
}
