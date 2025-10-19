package org.example.algorithms.sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SudokuSolver {

    public static void main(String[] args) {
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        new SudokuSolver().solveSudoku(board);
    }

    public void solveSudoku(char[][] board) {
        List<Set<Integer>> rowUniques = new ArrayList<>(9);
        List<Set<Integer>> colUniques = new ArrayList<>(9);
        List<Set<Integer>> boxUniques = new ArrayList<>(9);

        for (int i=0;i<9;i++) {
            rowUniques.add(new HashSet<>());
            colUniques.add(new HashSet<>());
            boxUniques.add(new HashSet<>());
        }

        if (!_helper(board, 0, 0, rowUniques, colUniques, boxUniques)) {
            throw new IllegalStateException();
        }
    }

    private boolean _helper(char[][] board, int i, int j, List<Set<Integer>> row, List<Set<Integer>> col, List<Set<Integer>> box) {
        // Print the board
        System.out.println("Sudoku board:");
        for (int r = 0; r < 9; r++) {
            if (r % 3 == 0 && r != 0) {
                System.out.println("------+-------+------");
            }
            for (int c = 0; c < 9; c++) {
                if (c % 3 == 0 && c != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
        char ch = board[i][j];
        if (ch != '.') {
            int num = (int) (ch - '0');

            if (row.get(i).contains(num)) return false;
            if (col.get(j).contains(num)) return false;
            int boxIndex = ((i/3) * 3) + (j / 3);
            if (box.get(boxIndex).contains(num)) return false;

            if (i == 8  && j == 8) return true;

            row.get(i).add(num);
            col.get(j).add(num);
            box.get(boxIndex).add(num);

            boolean isValidBoard = false;
            if (j == 8) {
                isValidBoard = _helper(board, i+1, 0, row, col, box);
            } else {
                isValidBoard = _helper(board, i, j+1, row, col, box);
            }

            return isValidBoard;
        }

        for (int k=1; k<=9; k++) {
            if (row.get(i).contains(k)) continue;
            if (col.get(j).contains(k)) continue;
            int boxIndex = ((i/3) * 3) + (j / 3);
            if (box.get(boxIndex).contains(k)) continue;
            board[i][j] = (char) (k + '0');

            if (i == 8  && j == 8) return true;

            row.get(i).add(k);
            col.get(j).add(k);
            box.get(boxIndex).add(k);

            boolean isValidBoard = false;

            if (j == 8) {
                isValidBoard = _helper(board, i+1, 0, row, col, box);
            } else {
                isValidBoard = _helper(board, i, j+1, row, col, box);
            }

            if (!isValidBoard) {
                board[i][j] = '.';
                row.get(i).remove(k);
                col.get(j).remove(k);
                box.get(boxIndex).remove(k);
            }
        }

        return false;
    }
}

// int box = ((i / 3) * 3) + (j / 3)