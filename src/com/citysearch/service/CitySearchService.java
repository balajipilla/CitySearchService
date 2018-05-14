package com.citysearch.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.citysearch.beans.TrieNode;
import com.citysearch.search.SearchCityWithPrefix;
import com.citysearch.utils.Constants;

/*
 * The service class to search for cities with given prefix and count of 
 * matching cities required.
 */
@Path("/SuggestCities")
public class CitySearchService {

	@POST
	@Path("/suggest_cities")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response findCitiesWithMatchingPrefix(
			@FormParam("prefix") String prefix, @FormParam("count") int count,
			@Context HttpServletRequest servletRequest) throws Exception {

		if (prefix.isEmpty()) {
			throw new IllegalArgumentException(
					" Prefix paramter is mandatory. ");
		}

		if (count <= 0) {
			count = Constants.DEFAULT_COUNT;
		}

		SearchCityWithPrefix searchCityWithPrefix = new SearchCityWithPrefix(
				prefix.toLowerCase(), count, getTrieDataStruxture());
		List<String> cities = searchCityWithPrefix.suggestCities();
		String result = cities.stream()
				.collect(Collectors.joining(Constants.LINE_SEPARATOR));
		CacheControl cc = new CacheControl();
		cc.setMaxAge(10000);
		return Response.ok(result).cacheControl(cc).build();
	}

	// Method to get the TrieNode structure for search
	private TrieNode getTrieDataStruxture() {

		Client client = ClientBuilder.newClient();

		TrieNode rootNode = client.target(Constants.INITIALIZE_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON).get()
				.readEntity(TrieNode.class);
		return rootNode;
	}
}
