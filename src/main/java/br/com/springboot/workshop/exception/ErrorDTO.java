package br.com.springboot.workshop.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int statusCode;
	private String message;
	private List<ErrorDetailDTO> details;

	public ErrorDTO(final HttpStatus statusCode, final String message, final List<ErrorDetailDTO> details) {
		this.statusCode = statusCode.value();
		this.details = details;
		this.message = message;
	}
	
	public ErrorDTO(final HttpStatus statusCode, final List<ErrorDetailDTO> details) {
		this.statusCode = statusCode.value();
		this.details = details;
	}

	public ErrorDTO(final HttpStatus statusCode, final String message) {
		this.statusCode = statusCode.value();
		this.message = message;
	}

	public ErrorDTO() {
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public List<ErrorDetailDTO> getDetails() {
		if (this.details == null) {
			this.details = new ArrayList<ErrorDetailDTO>();
		}
		return this.details;
	}

	public void setDetails(List<ErrorDetailDTO> details) {
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	

}
