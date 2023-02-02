package com.simplilearn.sportyshoe.exceptions;



public class ProductNotExistsException extends IllegalArgumentException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotExistsException(String msg) {
        super(msg);
    }}