package tests.com.citysearch.search;
import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.citysearch.beans.TrieNode;
import com.citysearch.search.SearchCityWithPrefix;
import com.citysearch.utils.ConstructData;

public class SearchCityWithPrefixTest {
	InputStream stream;
	TrieNode rootNode;

	
	
	@Before
	public void setUp() throws FileNotFoundException {
		stream = new FileInputStream(
				"src/tests/com/citysearch/utils/testCities.txt");
		rootNode = ConstructData.constructTrieDataStructure(stream);
	}

	@Test
	public void testSuggestCities() throws FileNotFoundException {
		SearchCityWithPrefix search = new SearchCityWithPrefix("cha", 10, rootNode);
		List<String> cities = search.suggestCities();
		assertEquals(cities.size(), 2);
		assert(cities.contains("Chatham"));
		assert(cities.contains("Chakragaon"));
	}
	
	@Test
	public void testSuggestCities2() throws FileNotFoundException {
		SearchCityWithPrefix search = new SearchCityWithPrefix("h", 1, rootNode);
		List<String> cities = search.suggestCities();
		assertEquals(cities.size(), 1);
	}
}
