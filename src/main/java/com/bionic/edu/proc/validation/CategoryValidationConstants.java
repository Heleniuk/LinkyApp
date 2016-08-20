package com.bionic.edu.proc.validation;

public interface CategoryValidationConstants {

	int TITLE_SIZE = 20;
	String TITLE_PATTERN = "[\\w\\d\\s]+";
	int DESCRIPTION_SIZE = 100;
	
	public static class Errors {
		public static final String TITLE_NOT_EMPTY_ERROR = "Title cannot be empty";
		public static final String TITLE_SIZE_ERROR = "Title cannot be longer than " + CategoryValidationConstants.TITLE_SIZE + " symbols";
		public static final String TITLE_PATTERN_ERROR = "Title should only contain letters, symbols or whitespace characters";
		public static final String DESCRIPTION_SIZE_ERROR = "Description cannot be longer than " + CategoryValidationConstants.DESCRIPTION_SIZE + " symbols";
	}
	
}
