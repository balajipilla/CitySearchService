package com.citysearch.service;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.citysearch.beans.TrieNode;
import com.citysearch.utils.Constants;
import com.citysearch.utils.ConstructData;

/*
 * Service to build the TrieNode structure based on the citis provided through file.
 */
@Path("/SuggestCities")
public class InitiateApplicationService {

	@GET
	@Path("/initiate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response initiateApplication() throws Exception {
		InputStream input = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(Constants.FILE_NAME);
		TrieNode rootNode = ConstructData.constructTrieDataStructure(input);
		CacheControl cc = new CacheControl();
		cc.setMaxAge(10000);
		return Response.status(200).entity(rootNode).cacheControl(cc).build();
	}
}
