/**
 *
 */
package org.ivavin.eventweather.exception;

/**
 * @author Ivano
 *
 */
public class EventServiceException extends Exception {

	private static final long serialVersionUID = -4637960283796278439L;

	/**
	 * @param message
	 */
	public EventServiceException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public EventServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
