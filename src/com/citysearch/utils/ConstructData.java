package com.citysearch.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.citysearch.beans.TrieNode;

/*
 * Constructs the TrieNode data structure to provide for efficient city search.
 */
public class ConstructData {

	public static TrieNode constructTrieDataStructure(InputStream inputStream)
			throws FileNotFoundException {

		TrieNode rootNode = new TrieNode(Constants.ROOT_NODE_VALUE,
				new TreeMap<Character, TrieNode>(), false);
		Scanner scanner = new Scanner(inputStream);
		try {

			while (scanner.hasNextLine()) {
				String city = scanner.nextLine();
				addCity(city.toLowerCase(), rootNode);

			}
		} catch (Exception exp) {
			throw exp;
		} finally {
			scanner.close();
		}
		return rootNode;
	}

	private static void addCity(String city, TrieNode parent) {
		Map<Character, TrieNode> childNodesMap = parent.getChildNodes();
		TrieNode newNode = null;
		char firstCharacter = city.charAt(0);
		
		if (childNodesMap.containsKey(firstCharacter)) {
			newNode = childNodesMap.get(firstCharacter);
		} else {
			newNode = new TrieNode(firstCharacter,
					new TreeMap<Character, TrieNode>(), false);
			childNodesMap.put(firstCharacter, newNode);
		}

		if (city.length() > 1) {
			addCity(city.substring(1), newNode);
		} else {
			newNode.setEnding(true);
		}
	}
}
