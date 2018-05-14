package com.citysearch.utils;

public final class Constants {

	private Constants() {

	}

	public static final int DEFAULT_COUNT = 5;

	public static final String FILE_NAME = "cities.txt";

	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	public final static char ROOT_NODE_VALUE = 0;

	public final static String INITIALIZE_SERVICE_URL = "http://localhost:8080/CitySearchService/rest/SuggestCities/initiate";
}
