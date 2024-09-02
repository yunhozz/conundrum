package programmers;

import java.util.ArrayList;
import java.util.List;

class 하노이의탑 {
    private final List<int[]> answers = new ArrayList<>();

    public int[][] solution(int n) {
        move(n, 1, 2, 3);
        return answers.toArray(int[][]::new);
    }

    private void move(int n, int start, int mid, int end) {
        if (n <= 0) return;
        move(n - 1, start, end, mid);
        answers.add(new int[]{start, end});
        move(n - 1, mid, start, end);
    }
}