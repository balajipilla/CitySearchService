package tests.com.citysearch.utils;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import com.citysearch.beans.TrieNode;
import com.citysearch.utils.ConstructData;

public class ConstructDataTest {

	InputStream stream;
	TrieNode rootNode;

	@Before
	public void setUp() throws FileNotFoundException {
		stream = new FileInputStream(
				"src/tests/com/citysearch/utils/testCities.txt");
		rootNode = ConstructData.constructTrieDataStructure(stream);
	}

	/*
	 * Test case to correctness of child nodes size and their values
	 */
	@Test
	public void testTrieStructureData() throws FileNotFoundException {

		assertEquals(11, rootNode.getChildNodes().size());
		assertEquals(rootNode.getChildNodes().get('c').getChildNodes().size(), 2);
		assertEquals(rootNode.getChildNodes().get('c').isEnding, false);
		assert (rootNode.getChildNodes().get('c').getChildNodes().containsKey('a'));
		assert (rootNode.getChildNodes().get('c').getChildNodes().containsKey('h'));
	}


	/*
	 * Test case to correctness of ending flag.
	 */
	@Test
	public void testEndingFlag() throws FileNotFoundException {
		TrieNode endingNode = rootNode.getChildNodes().get('a').getChildNodes()
				.get('a').getChildNodes().get('a');
		assert (endingNode.isEnding);
	}

}
