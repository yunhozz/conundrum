package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 토마토 {

    private static int[][] board;
    private static boolean[][] check;

    private static Queue<int[]> q = new LinkedList<>();
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        int m = Integer.parseInt(size[0]);
        int n = Integer.parseInt(size[1]);

        board = new int[n][m];
        check = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            int idx = 0;

            for (String l : line) {
                int num = Integer.parseInt(l);
                if (num == 1) {
                    q.offer(new int[] {i, idx, 0});
                    check[i][idx] = true;
                }
                board[i][idx++] = num;
            }
        }

        System.out.println(bfs(n, m));
    }

    private static int bfs(int n, int m) {
        int max = 0;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            max = Math.max(now[2], max);

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (!check[nx][ny] && board[nx][ny] == 0) {
                        check[nx][ny] = true;
                        board[nx][ny] = 1;
                        q.offer(new int[] {nx, ny, now[2] + 1});
                    }
                }
            }
        }

        for (int[] line : board) {
            String lineStr = Arrays.toString(line);
            if (lineStr.contains("0")) return -1;
        }

        return max;
    }
}
