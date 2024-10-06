package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _2239 {

    static int[][] board;
    static final List<int[]> coordinates = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[9][9];

        for (int i = 0; i < 9; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < input.length; j++) {
                board[i][j] = Integer.parseInt(input[j]);
                if (board[i][j] == 0) {
                    coordinates.add(new int[]{i, j});
                }
            }
        }

        makeBoard(0);
    }

    static void makeBoard(int depth) {
        if (depth >= coordinates.size()) {
            StringBuilder sb = new StringBuilder();
            for (int[] b : board) {
                Arrays.stream(b).forEach(sb::append);
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }

        int x = coordinates.get(depth)[0];
        int y = coordinates.get(depth)[1];

        for (int i = 1; i <= 9; i++) {
            if (checkBoard(x, y, i)) {
                board[x][y] = i;
                makeBoard(depth + 1);
                board[x][y] = 0;
            }
        }
    }

    static boolean checkBoard(int x, int y, int value) {
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == value) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == value) {
                return false;
            }
        }

        int _x = x / 3 * 3;
        int _y = y / 3 * 3;

        for (int i = _x; i < _x + 3; i++) {
            for (int j = _y; j < _y + 3; j++) {
                if (board[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }
}