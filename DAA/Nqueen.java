package com.nov13;

public class Nqueen {
	    private int N;
	    private int[] board;

	    public Nqueen(int N) {
	        this.N = N;
	        board = new int[N];
	    }

	    private boolean isSafe(int row, int col) {
	        for (int i = 0; i < row; i++) {
	            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row)) {
	                return false;
	            }
	        }
	        return true;
	    }

	    private boolean solve(int row) {
	        if (row == N) {
	            for (int i = 0; i < N; i++) {
	                for (int j = 0; j < N; j++) {
	                    System.out.print(board[i] == j ? "Q " : ". ");
	                }
	                System.out.println();
	            }
	            System.out.println();
	            return false;  
	        }
	        for (int col = 0; col < N; col++) {
	            if (isSafe(row, col)) {
	                board[row] = col;
	                if (solve(row + 1)) return true;
	                board[row] = -1;  
	            }
	        }
	        return false;
	    }

	    public static void main(String[] args) {
	        new Nqueen(4).solve(0);
	    }
	
	}

