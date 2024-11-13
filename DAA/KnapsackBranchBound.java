package com.nov13;
import java.util.*;

public class KnapsackBranchBound {

    static class Node {
        int level, profit, weight;
        double bound;

        public Node(int level, int profit, int weight, double bound) {
            this.level = level;
            this.profit = profit;
            this.weight = weight;
            this.bound = bound;
        }
    }

    public static double bound(Node u, int n, int[] values, int[] weights, int capacity) {
        if (u.weight >= capacity) return 0;

        int profitBound = u.profit;
        int j = u.level + 1;
        int totalWeight = u.weight;

        while (j < n && totalWeight + weights[j] <= capacity) {
            totalWeight += weights[j];
            profitBound += values[j];
            j++;
        }

        if (j < n) {
            profitBound += (capacity - totalWeight) * values[j] / (double) weights[j];
        }

        return profitBound;
    }

    public static int knapsack(int[] values, int[] weights, int capacity, int n) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            indices.add(i);
        }
        indices.sort((i, j) -> Double.compare((double) values[j] / weights[j], (double) values[i] / weights[i]));

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble((Node node) -> -node.bound));

        Node root = new Node(-1, 0, 0, 0);
        pq.add(root);

        int maxProfit = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.level == n - 1) continue;
            if (current.profit > maxProfit) {
                maxProfit = current.profit;
            }

            Node nextNode = new Node(current.level + 1, current.profit + values[current.level + 1], current.weight + weights[current.level + 1], 0);
            if (nextNode.weight <= capacity) {
                nextNode.bound = bound(nextNode, n, values, weights, capacity);
                if (nextNode.bound > maxProfit) {
                    pq.add(nextNode);
                }
            }

            nextNode = new Node(current.level + 1, current.profit, current.weight, 0);
            nextNode.bound = bound(nextNode, n, values, weights, capacity);
            if (nextNode.bound > maxProfit) {
                pq.add(nextNode);
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;  // Knapsack capacity
        int n = values.length;

        int maxProfit = knapsack(values, weights, capacity, n);

        System.out.println("Maximum profit in Knapsack: " + maxProfit);
    }
}
