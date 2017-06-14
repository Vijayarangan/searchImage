package com.vijay.searchImage.utils;

public final class SearchStringUtils {
	
	public static String cleanseString(String incomingString){
		return incomingString.replaceAll("[^\\p{Alpha} ]", "");
	}
	
	

}
