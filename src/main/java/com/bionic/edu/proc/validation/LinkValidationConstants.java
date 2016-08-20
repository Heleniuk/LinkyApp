package com.bionic.edu.proc.validation;

public interface LinkValidationConstants {

	int LINK_ADDRESS_SIZE = 2000;
	String LINK_ADDRESS_PATTERN = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	int TITLE_SIZE = 20;
	int DESCRIPTION_SIZE = 100;
	int RATING_MAX_VALUE = 5;
	int RATING_MIN_VALUE = 0;
	int CATEGORIES_SIZE = 6;
	
	public static class Errors {
		public static final String LINK_ADDRESS_SIZE_ERROR = "Link address cannot be longer than " + LinkValidationConstants.LINK_ADDRESS_SIZE +" symbols";
		public static final String LINK_ADDRESS_NOT_EMPTY_ERROR = "Link address cannot be empty";
		public static final String LINK_ADDRESS_PATTERN_ERROR = "Invalid link";
		public static final String TITLE_NOT_EMPTY_ERROR = "Title cannot be empty";
		public static final String TITLE_SIZE_ERROR = "Title cannot be longer than " + LinkValidationConstants.TITLE_SIZE + " symbols";
		public static final String DESCRIPTION_SIZE_ERROR = "Description cannot be longer than " + LinkValidationConstants.DESCRIPTION_SIZE + " symbols";
		public static final String RATING_MAX_VALUE_ERROR = "Rating cannot be more than + " + LinkValidationConstants.RATING_MAX_VALUE + " stars";
		public static final String RATING_MIN_VALUE_ERROR = "Rating cannot be less than + " + LinkValidationConstants.RATING_MIN_VALUE + " stars";
		public static final String CATEGORIES_SIZE_ERROR = "A link can be tied to no more than " + LinkValidationConstants.CATEGORIES_SIZE + " categories";
		public static final String CATEGORIES_NOT_EMPTY_ERROR = "A link should be tied to a category";
	}
	
}
