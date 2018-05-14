package com.citysearch.beans;

import java.util.TreeMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.citysearch.utils.Constants;
/*
 * This class is used to store the cities information. Each TrieNode contains its value,
 * child nodes, which are TrieNodes ah=gain, and a boolean flag to indicate if the 
 * TrieNode's value is end of a city
 */
@XmlRootElement(name = "trieNode")
@XmlAccessorType(XmlAccessType.FIELD)
public final class TrieNode {

	public TrieNode(char value, TreeMap<Character, TrieNode> childNodes,
			boolean isEnding) {
		this.value = value;
		this.childNodes = childNodes;
		this.isEnding = isEnding;
	}

	public TrieNode() {
		childNodes = new TreeMap<Character, TrieNode>();
		isEnding = false;
		value = Constants.ROOT_NODE_VALUE;
	}

	@XmlElement(name = "value", required = true)
	public char value;

	@XmlElement(name = "childNodes", required = true)
	public TreeMap<Character, TrieNode> childNodes;

	@XmlElement(name = "IsEnding", required = true)
	public boolean isEnding;

	public boolean getEnding() {
		return isEnding;
	}

	public void setEnding(boolean isEnding) {
		this.isEnding = isEnding;
	}

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public TreeMap<Character, TrieNode> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(TreeMap<Character, TrieNode> childNodes) {
		this.childNodes = childNodes;
	}

}
