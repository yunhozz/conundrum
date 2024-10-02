package backjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class _13913 {

    static int[] route;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int time = bfs(N, K);
        System.out.println(time);

        Stack<Integer> st = new Stack<>();
        st.push(K);
        int index = K;
        while (index != N) {
            int previous = route[index];
            st.push(previous);
            index = previous;
        }

        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.append(st.pop()).append(" ");
        }
        System.out.println(sb);
    }

    private static int bfs(int n, int k) {
        Queue<int []> q = new LinkedList<>();
        route = new int[100001];
        boolean[] visited = new boolean[100001];

        q.offer(new int[]{n, 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int location = now[0];
            int time = now[1];

            if (location == k) return time;

            if (location + 1 <= 100000 && !visited[location + 1]) {
                visited[location + 1] = true;
                q.offer(new int[]{location + 1, time + 1});
                route[location + 1] = location;
            }
            if (location - 1 >= 0 && !visited[location - 1]) {
                visited[location - 1] = true;
                q.offer(new int[]{location - 1, time + 1});
                route[location - 1] = location;
            }
            if (location * 2 <= 100000 && !visited[location * 2]) {
                visited[location * 2] = true;
                q.offer(new int[]{location * 2, time + 1});
                route[location * 2] = location;
            }
        }
        return -1;
    }
}