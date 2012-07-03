/**
 * 
 */
package ch.krizi.exceptionhandler.wrapper;

/**
 * Throw this exception if exception-wrapping failed.
 * 
 * @author krizi
 * 
 */
public class WrapperException extends RuntimeException {

	private static final long serialVersionUID = 4895594247877490968L;

	/**
	 * @param arg0
	 */
	public WrapperException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public WrapperException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public WrapperException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
