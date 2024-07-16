package programmers;

import java.util.ArrayList;
import java.util.Arrays;

public class 모의고사 {

    public int[] solution(int[] answers) {
        int[] p1 = {1, 2, 3, 4, 5};
        int[] p2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] p3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int c1 = func(answers, p1);
        int c2 = func(answers, p2);
        int c3 = func(answers, p3);
        int max = Math.max(c1, Math.max(c2, c3));

        ArrayList<Integer> result = new ArrayList<>() {{
            if (c1 - max >= 0) add(1);
            if (c2 - max >= 0) add(2);
            if (c3 - max >= 0) add(3);
        }};

        return result.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    private int func(int[] answers, int[] p) {
        int count = 0;
        for (int i = 0; i <= answers.length / p.length; i++) {
            int[] arr = Arrays.copyOfRange(answers, i * p.length, (i + 1) * p.length);
            for (int j = 0; j < p.length; j++) {
                if (arr[j] == p[j]) count ++;
            }
        }
        return count;
    }
}
