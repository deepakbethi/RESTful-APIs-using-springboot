package com.example.ProductService.exception;

import lombok.Data;

@Data
public class ProductServiceCoustomException extends RuntimeException {
	
	private String errorCode;
	public ProductServiceCoustomException(String message,String errorCode) {
		
		super(message);
		this.errorCode=errorCode;
	}

}
