package backjoon;

import java.util.Scanner;

public class _2661 {

    static final int[] arr = {1, 2, 3};
    static int n;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        makeArr(0, "");
    }

    static void makeArr(int depth, String str) {
        if (depth == n) {
            System.out.println(str);
            System.exit(0);
        }

        for (int i = 0; i < 3; i++) {
            if (checkStr(str + arr[i])) {
                makeArr(depth + 1, str + arr[i]);
            }
        }
    }

    static boolean checkStr(String str) {
        for (int i = 1; i <= str.length() / 2; i++) {
            String str1 = str.substring(str.length() - i * 2, str.length() - i);
            String str2 = str.substring(str.length() - i);
            if (str1.equals(str2)) return false;
        }
        return true;
    }
}