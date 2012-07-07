/**
 * 
 */
package ch.krizi.exceptionhandler.wrapper.exception;

/**
 * @author krizi
 *
 */
public class MyOwnException extends RuntimeException {

	private static final long serialVersionUID = -1259207673625981949L;

	public MyOwnException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyOwnException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public MyOwnException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MyOwnException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
}
