package com.citysearch.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import com.citysearch.beans.TrieNode;

/*
 * The search handler, which search through the TrieNode structure using the prefix, 
 * count and rootNode.
 */
public class SearchCityWithPrefix {

	public SearchCityWithPrefix(String prefix, int count, TrieNode rootNode) {

		this.prefix = prefix.toLowerCase();
		this.count = count;
		this.rootNode = rootNode;
	}

	private String prefix;
	private int count;
	private TrieNode rootNode;

	public List<String> suggestCities() {
		List<String> result = findMatchingCities(rootNode, prefix, count, "",
				0);
		// Returns cities based on the count.
		if (result.size() > count) {
			return result.subList(0, count);
		}
		return result;
	}

	// Search through the data structure to find the node with which the prefix ends.
	private static List<String> findMatchingCities(TrieNode rootNode,
			String prefix, int count, String currentWord, int currentCount) {
		List<String> matchingCities = new ArrayList<String>();
		TreeMap<Character, TrieNode> childNodes = rootNode.getChildNodes();
		Iterator<Character> keys = childNodes.keySet().iterator();

		while (keys.hasNext()) {
			TrieNode curNode = childNodes.get(keys.next());

			if (curNode.getValue() == prefix.charAt(0)) {
				if (prefix.length() == 1) { //Only the last character in prefix is left to match
					matchingCities.addAll(
							getCitiesWithPrefix(curNode, currentWord + prefix));

					currentCount = currentCount + matchingCities.size();
					if (count <= currentCount) {
						return matchingCities;
					}
				}

				if (prefix.length() > 1) {
					matchingCities.addAll(findMatchingCities(curNode,
							prefix.substring(1), count,
							currentWord + curNode.getValue(), currentCount));
				}
			}
		}

		return matchingCities;

	}

	// Gets all the cities from child nodes of the given node.
	private static List<String> getCitiesWithPrefix(TrieNode node,
			String prefix) {
		TreeMap<Character, TrieNode> childNodes = node.getChildNodes();
		Iterator<Character> keys = childNodes.keySet().iterator();
		ArrayList<String> cities = new ArrayList<String>();
		while (keys.hasNext()) {
			TrieNode childNode = childNodes.get(keys.next());
			String newCity = prefix + childNode.getValue();
			if (childNode.getEnding()) {
				cities.add(getDisplayString(newCity));
				continue;
			}
			cities.addAll(getCitiesWithPrefix(childNode, newCity));
		}
		return cities;
	}

	// Changing the first character in the city name to upper case for display.
	private static String getDisplayString(String value) {
		return Character.toUpperCase(value.charAt(0)) + value.substring(1);
	}

}
