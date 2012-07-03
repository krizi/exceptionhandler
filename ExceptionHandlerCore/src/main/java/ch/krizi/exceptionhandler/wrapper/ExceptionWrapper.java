/**
 * 
 */
package ch.krizi.exceptionhandler.wrapper;

/**
 * Interface to wrap Exceptions
 * 
 * @author krizi
 * 
 */
public interface ExceptionWrapper<Throw extends Throwable, WrapTo extends Throwable> {
	public WrapTo wrap(Throw throwedException, Class<? extends WrapTo> wrapTo, String message) throws Throwable;
}
