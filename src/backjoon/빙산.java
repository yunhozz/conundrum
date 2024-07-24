package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 빙산 {

    private static int[][] board;
    private static int n, m;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            int idx = 0;

            for (String l : line) {
                int num = Integer.parseInt(l);
                board[i][idx++] = num;
            }
        }

        int answer = 0;

        while (true) {
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] > 0)
                        q.offer(new int[] {i, j});
                }
            }

            boolean[][] check = new boolean[n][m];
            int count = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] > 0 && !check[i][j]) {
                        dfs(i, j, check);
                        count++;
                    }
                }
            }

            if (count >= 2) {
                System.out.println(answer);
                break;
            }
            if (count == 0) {
                System.out.println(0);
                break;
            }

            bfs(q);
            answer++;
        }
    }

    private static void dfs(int x, int y, boolean[][] check) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                if (!check[nx][ny] && board[nx][ny] > 0) {
                    check[nx][ny] = true;
                    dfs(nx, ny, check);
                }
            }
        }
    }

    private static void bfs(Queue<int[]> q) {
        boolean[][] check = new boolean[n][m];
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            int count = 0;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (board[nx][ny] == 0 && !check[nx][ny] && board[x][y] > 0) {
                        check[x][y] = true;
                        count++;
                    }
                }
            }

            board[x][y] = Math.max(board[x][y] - count, 0);
        }
    }
}
