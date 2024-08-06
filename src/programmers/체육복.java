package programmers;

import java.util.Arrays;

public class 체육복 {

    public int solution(int n, int[] lost, int[] reserve) {
        int[] students = new int[n];
        for (int l : lost) {
            students[l - 1] --;
        }
        for (int r : reserve) {
            students[r - 1] ++;
        }

        for (int i = 0; i < students.length - 1; i++) {
            if (students[i] > 0 && students[i + 1] < 0) {
                students[i] --;
                students[i + 1] ++;
            }
            if (students[i] < 0 && students[i + 1] > 0) {
                students[i] ++;
                students[i + 1] --;
            }
        }

        return (int) Arrays.stream(students)
                .filter(s -> s >= 0)
                .count();
    }
}
