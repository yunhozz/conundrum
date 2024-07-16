package programmers;

import java.util.HashSet;

public class 소수찾기 {

    static String[] arr;
    static boolean[] sosu, check;
    static HashSet<Integer> set = new HashSet<>();

    public int solution(String numbers) {
        arr = numbers.split("");
        check = new boolean[arr.length];
        sosu = new boolean[10000000];

        sosu = makeSosu(sosu);
        dfs(0, "");

        int result = 0;
        for (Integer n : set) {
            if (!sosu[n]) result++;
        }
        return result;
    }

    static void dfs(int depth, String str) {
        if (depth == arr.length) {
            if (!str.isEmpty()) {
                int num = Integer.parseInt(str);
                set.add(num);
            }
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!check[i]) {
                check[i] = true;
                dfs(depth + 1, str + arr[i]);
                check[i] = false;
                dfs(depth + 1, str);
            }
        }
    }

    static boolean[] makeSosu(boolean[] sosu) {
        sosu[0] = sosu[1] = true;
        for (int i = 2; i <= Math.sqrt(sosu.length); i++) {
            if (sosu[i]) continue;
            for (int j = i * i; j < sosu.length; j += i) {
                sosu[j] = true;
            }
        }
        return sosu;
    }
}
