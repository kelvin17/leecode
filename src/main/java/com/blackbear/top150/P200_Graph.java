package com.blackbear.top150;

import java.util.LinkedList;
import java.util.Queue;

public class P200_Graph {

    public static void main(String[] args) {

        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        P200_Graph graph = new P200_Graph();
        System.out.printf("Count of Islands: %d\n", graph.numIslands(grid));

    }

    class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int numIslands(char[][] grid) {
        int count = 0;

        int rows = grid.length;
        int columns = grid[0].length;

        int[][] direction = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        boolean[][] visited = new boolean[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (visited[i][j]) {
                    continue;
                }

                visited[i][j] = true;

                if (grid[i][j] == '1') {
                    count++;
                    //in land -> expand to edge
                    Pair loc = new Pair(i, j);
                    Queue<Pair> unchecked = new LinkedList<>();
                    unchecked.add(loc);
                    while (!unchecked.isEmpty()) {
                        Pair p = unchecked.poll();
                        for (int[] item : direction) {
                            int row = p.row + item[0];
                            int col = p.col + item[1];
                            if (row >= 0 && row < rows && col >= 0 && col < columns) {
                                if (grid[row][col] == '1' && !visited[row][col]) {
                                    Pair newLoc = new Pair(row, col);
                                    unchecked.add(newLoc);
                                }
                                visited[row][col] = true;
                            }
                        }
                    }
                }
            }
        }

        return count;

    }

}
