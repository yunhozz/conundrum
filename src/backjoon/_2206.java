package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class _2206 {

    private static int[][] board;
    private static boolean[][][] checked;
    private static int n, m;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");

        n = Integer.parseInt(size[0]);
        m = Integer.parseInt(size[1]);
        board = new int[n][m];
        checked = new boolean[n][m][2];

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split("");
            int idx = 0;
            for (String s : line) {
                int num = Integer.parseInt(s);
                board[i][idx++] = num;
            }
        }

        q.offer(new int[] {0, 0, 0, 1});
        checked[0][0][0] = true;
        bfs(q);
    }

    private static void bfs(Queue<int[]> q) {
        int answer = 0;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int count = now[2];
            int energy = now[3];

            if (now[0] == n - 1 && now[1] == m - 1) {
                System.out.println(count + 1);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (board[nx][ny] == 1) {
                        if (!checked[nx][ny][1] && energy > 0) {
                            checked[nx][ny][1] = true;
                            q.offer(new int[] {nx, ny, count + 1, 0});
                        }
                    } else {
                        if (!checked[nx][ny][0] && energy > 0) {
                            checked[nx][ny][0] = true;
                            q.offer(new int[] {nx, ny, count + 1, 1});

                        } else if (!checked[nx][ny][1] && energy == 0) {
                            checked[nx][ny][1] = true;
                            q.offer(new int[] {nx, ny, count + 1, 0});
                        }
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
