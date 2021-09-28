package com.product.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(content = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductErrorVO {

	@JsonProperty("exception")
	private Class<? extends Exception> exception;
	
	@JsonProperty("message")
	private String message;
	
	public ProductErrorVO() {
	}

	public ProductErrorVO(Class<? extends Exception> class1, String message) {
	        super();
	        this.exception = class1;
	        this.message = message;
	    }

	public Class<? extends Exception> getException() {
		return exception;
	}

	public void setException(Class<? extends Exception> exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}