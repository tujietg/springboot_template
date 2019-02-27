package com.tujietg.Exception;

public class UnauthorizedException extends RuntimeException {

	public UnauthorizedException(String msg) {
		super(msg);
	}

	public UnauthorizedException() {
		super();
	}
}
