package programmers;

import java.util.Arrays;

class 최고의집합 {
    public int[] solution(int n, int s) {
        if (n > s) return new int[]{-1};

        int[] arr = new int[n];
        Arrays.fill(arr, s / n);

        int remainder = s % n;

        for (int i = 0; i < remainder; i++) {
            arr[n - 1 - i]++;
        }

        return arr;
    }
}