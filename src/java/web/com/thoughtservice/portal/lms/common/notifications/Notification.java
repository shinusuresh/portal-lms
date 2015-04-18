package com.thoughtservice.portal.lms.common.notifications;

/**
 * Notification messages object. This object is being used to transfer status
 * messages from action layer to view (jsp) layer.
 * 
 * @author Shinu
 *
 */
public class Notification {

	public enum STATUS {
		/**
		 * Use if state of transaction is success
		 */
		SUCCESS, 
		/**
		 * Use if state if transaction is failure
		 */
		WARNING, 
		/**
		 * Use for showing up warning messages
		 */
		ERROR;

		public String getString() {
			return this.name();
		}
	};

	private String message;
	private String code;
	private STATUS status;

	public Notification(String message, String code, STATUS status) {
		this.message = message;
		this.code = code;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

}
