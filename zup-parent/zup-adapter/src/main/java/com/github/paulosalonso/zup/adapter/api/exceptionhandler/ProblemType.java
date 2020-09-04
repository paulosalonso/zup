package com.github.paulosalonso.zup.adapter.api.exceptionhandler;

import org.springframework.http.HttpStatus;

public enum ProblemType {

	NOT_FOUND("Not found"),
	INTEGRITY_VIOLATION("Integrity violation"),
	UNRECOGNIZED_MESSAGE("Unrecognized message"),
	INVALID_PARAMETER("Invalid parameter"),
	INVALID_DATA("Invalid data"),
	INTERNAL_ERROR("Internal error"),
	LOCKED("Locked resource");
	
	private String title;
	
	private ProblemType(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public static ProblemType getByStatusCode(HttpStatus httpStatus) {
		switch (httpStatus) {
			case NOT_FOUND: return NOT_FOUND;
			case LOCKED: return LOCKED;
			case BAD_REQUEST: return INVALID_DATA;
			default: return INTERNAL_ERROR;
		}
	}
	
}
