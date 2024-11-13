package com.nov13;

import java.util.Scanner;

public class FibonacciSeries {
    public static int fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }

        int fib = 1;
        int prevFib = 0;

        for (int i = 2; i <= n; i++) {
            int temp = fib;
            fib = fib + prevFib;
            prevFib = temp;
        }
        return fib;
    }

    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();

        System.out.println("Iterative Fibonacci of " + n + ": " + fibonacciIterative(n));
        System.out.println("Recursive Fibonacci of " + n + ": " + fibonacciRecursive(n));
        
        scanner.close();
    }
}
