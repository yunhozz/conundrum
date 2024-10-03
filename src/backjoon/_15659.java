package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class _15659 {

    static List<Integer> numbers = new ArrayList<>();
    static List<String> operators = new ArrayList<>();
    static String[] opsArr;
    static boolean[] visited;
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] input1 = br.readLine().split(" ");
        String[] input2 = br.readLine().split(" ");

        for (String s : input1) {
            numbers.add(Integer.parseInt(s));
        }

        String[] ops = {"+", "-", "*", "/"};
        for (int i = 0; i < input2.length; i++) {
            int opsNum = Integer.parseInt(input2[i]);
            for (int j = 0; j < opsNum; j++) {
                operators.add(ops[i]);
            }
        }

        opsArr = new String[n - 1];
        visited = new boolean[n - 1];
        dfs(0);

        System.out.println(max + "\n" + min);
    }

    static void dfs(int depth) {
        if (depth == operators.size()) {
            List<Integer> tempNumbers = new ArrayList<>(numbers);
            List<String> tempOperators = new ArrayList<>(List.of(opsArr));

            while (!tempOperators.isEmpty()) {
                boolean flag = false;
                for (String operator : tempOperators) {
                    if (operator.equals("*") || operator.equals("/")) {
                        flag = true;
                        break;
                    }
                }

                for (int i = 0; i < tempOperators.size(); i++) {
                    String to = tempOperators.get(i);
                    if (flag && (to.equals("+") || to.equals("-"))) continue;

                    String operator = tempOperators.remove(i);
                    Integer num = null;
                    switch (operator) {
                        case "+":
                            num = tempNumbers.remove(i) + tempNumbers.remove(i);
                            break;
                        case "-":
                            num = tempNumbers.remove(i) - tempNumbers.remove(i);
                            break;
                        case "*":
                            num = tempNumbers.remove(i) * tempNumbers.remove(i);
                            break;
                        case "/":
                            num = tempNumbers.remove(i) / tempNumbers.remove(i);
                            break;
                    }
                    tempNumbers.add(i, num);
                    break;
                }
            }

            Integer result = tempNumbers.get(0);
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for (int i = 0; i < operators.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                opsArr[i] = operators.get(depth);
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}