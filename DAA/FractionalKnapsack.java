package com.nov13;

import java.util.*;

class Item {
	int value;
	int weight;

	Item(int value, int weight) {
		this.value = value;
		this.weight = weight;
	}
}

public class FractionalKnapsack {

	public static double getMaxValue(Item[] items, int capacity) {
		Arrays.sort(items, (a, b) -> Double.compare((double) b.value / b.weight, (double) a.value / a.weight));

		double totalValue = 0.0;
		int currentWeight = 0;

		for (Item item : items) {
			if (currentWeight + item.weight <= capacity) {
				totalValue += item.value;
				currentWeight += item.weight;
			} else {
				int remainingWeight = capacity - currentWeight;
				totalValue += item.value * ((double) remainingWeight / item.weight);
				break;
			}
		}

		return totalValue;
	}

	public static void main(String[] args) {
		Item[] items = { new Item(60, 10), new Item(100, 20), new Item(120, 30) };
		int capacity = 50;

		double maxValue = getMaxValue(items, capacity);
		System.out.println("Maximum value in Knapsack = " + maxValue);
	}
}
