package com.nov13;

import java.util.*;

class Node {
	char character;
	int frequency;
	Node left, right;

	Node(char character, int frequency) {
		this.character = character;
		this.frequency = frequency;
		this.left = null;
		this.right = null;
	}

	Node(int frequency, Node left, Node right) {
		this.character = '\0';
		this.frequency = frequency;
		this.left = left;
		this.right = right;
	}
}

public class HuffmanCoding {

	public static Node buildHuffmanTree(Map<Character, Integer> frequencyMap) {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.frequency));

		for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
			pq.add(new Node(entry.getKey(), entry.getValue()));
		}

		while (pq.size() > 1) {
			Node left = pq.poll();
			Node right = pq.poll();

			Node newNode = new Node(left.frequency + right.frequency, left, right);

			pq.add(newNode);
		}

		return pq.poll();
	}

	public static void generateHuffmanCodes(Node root, String prefix, Map<Character, String> codebook) {
		if (root == null) {
			return;
		}

		if (root.left == null && root.right == null) {
			codebook.put(root.character, prefix);
		}

		generateHuffmanCodes(root.left, prefix + '0', codebook);
		generateHuffmanCodes(root.right, prefix + '1', codebook);
	}

	public static String encode(String text, Map<Character, String> huffmanCodes) {
		StringBuilder encodedText = new StringBuilder();
		for (char c : text.toCharArray()) {
			encodedText.append(huffmanCodes.get(c));
		}
		return encodedText.toString();
	}

	public static String decode(String encodedText, Node root) {
		StringBuilder decodedText = new StringBuilder();
		Node currentNode = root;
		for (int i = 0; i < encodedText.length(); i++) {
			if (encodedText.charAt(i) == '0') {
				currentNode = currentNode.left;
			} else {
				currentNode = currentNode.right;
			}

			if (currentNode.left == null && currentNode.right == null) {
				decodedText.append(currentNode.character);
				currentNode = root;
			}
		}
		return decodedText.toString();
	}

	public static void main(String[] args) {
		String text = "My Name is Monika";

		Map<Character, Integer> frequencyMap = new HashMap<>();
		for (char c : text.toCharArray()) {
			frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
		}

		Node root = buildHuffmanTree(frequencyMap);

		Map<Character, String> huffmanCodes = new HashMap<>();
		generateHuffmanCodes(root, "", huffmanCodes);

		String encodedText = encode(text, huffmanCodes);
		System.out.println("Encoded Text: " + encodedText);
		System.out.println("Huffman Codes: " + huffmanCodes);

		String decodedText = decode(encodedText, root);
		System.out.println("Decoded Text: " + decodedText);
	}
}
