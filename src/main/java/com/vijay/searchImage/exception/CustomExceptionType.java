package com.vijay.searchImage.exception;

public class CustomExceptionType {
	private String errorMessage;

    public CustomExceptionType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
