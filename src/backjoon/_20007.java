package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class _20007 {

    static int N, M, X, Y, INF = Integer.MAX_VALUE;
    static final List<List<Point>> points = new ArrayList<>();

    static class Point implements Comparable<Point> {
        int num, length;

        public Point(int num, int length) {
            this.num = num;
            this.length = length;
        }

        @Override
        public int compareTo(Point p) {
            return length - p.length;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            points.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            points.get(h1).add(new Point(h2, l));
            points.get(h2).add(new Point(h1, l));
        }

        int[] length = dijkstra(Y);
        Queue<Point> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            if (i == Y) continue;
            if (length[i] == INF) {
                System.out.println(-1);
                System.exit(0);
            }
            pq.offer(new Point(i, length[i]));
        }

        int sum = 0;
        int answer = 1;
        boolean flag = true;

        while (!pq.isEmpty()) {
            Point point = pq.peek();

            sum += point.length * 2;

            if (sum > X) {
                sum = point.length * 2;

                if (sum <= X) {
                    pq.poll();
                    answer++;
                } else {
                    flag = false;
                    break;
                }
            } else {
                pq.poll();
            }
        }

        System.out.println(flag ? answer : -1);
    }

    static int[] dijkstra(int start) {
        Queue<Point> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];
        int[] length = new int[N];

        Arrays.fill(length, INF);
        pq.offer(new Point(start, 0));
        length[start] = 0;

        while (!pq.isEmpty()) {
            Point point = pq.poll();

            if (length[point.num] < point.length) continue;
            if (visited[point.num]) continue;

            visited[point.num] = true;
            List<Point> pointList = points.get(point.num);

            for (Point p : pointList) {
                if (length[p.num] > length[point.num] + p.length) {
                    length[p.num] = length[point.num] + p.length;
                    pq.offer(new Point(p.num, length[p.num]));
                }
            }
        }

        return length;
    }
}