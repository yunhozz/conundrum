package programmers;

import java.util.ArrayList;
import java.util.Collections;

public class 카펫 {

    public int[] solution(int brown, int yellow) {
        ArrayList<Integer> arr = new ArrayList<>();
        int sqrt = (int) Math.sqrt(yellow);

        for (int i = 1; i <= sqrt; i++) {
            if (yellow % i == 0){
                arr.add(i);
                arr.add(yellow / i);
            }
        }
        arr.sort(Collections.reverseOrder());

        for (int i = 0; i < arr.size() / 2; i++) {
            Integer a = arr.get(i);
            Integer b = arr.get(arr.size() - i - 1);
            int num = 2 * (a + b) + 4;

            if (num <= brown) {
                if ((brown - num) % 8 == 0) {
                    int r = (brown - num) / 8;
                    return new int[] {a + r * 2 + 2, b + r * 2 + 2};
                }
            }
        }

        return null;
    }
}
