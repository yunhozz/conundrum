package programmers;

import java.util.ArrayList;
import java.util.List;

public class 수식최대화 {

    private long answer = Long.MIN_VALUE;
    private char[] ops, priorities;
    private boolean[] check;
    private List<Long> numbers;
    private List<Character> operators;

    public long solution(String expression) {
        numbers = new ArrayList<>();
        operators = new ArrayList<>();
        ops = new char[] {'+', '-', '*'};
        priorities = new char[3];
        check = new boolean[3];

        int idx = 0;
        for (int i = 0; i < expression.length(); i++) {
            char e = expression.charAt(i);
            if (e == '+' || e == '-' || e == '*') {
                numbers.add(Long.parseLong(expression.substring(idx, i)));
                operators.add(e);
                idx = i + 1;
            }
        }
        numbers.add(Long.parseLong(expression.substring(idx)));

        dfs(0);

        return answer;
    }

    private void dfs(int depth) {
        if (depth == 3) {
            List<Long> tempNumbers = new ArrayList<>(numbers);
            List<Character> tempOperators = new ArrayList<>(operators);

            while (!tempOperators.isEmpty()) {
                int maxPriority = 0;
                for (Character to : tempOperators) {
                    int priority = getPriority(to);
                    maxPriority = Math.max(priority, maxPriority);
                }

                for (int i = 0; i < tempOperators.size(); i++) {
                    Character to = tempOperators.get(i);
                    if (getPriority(to) == maxPriority) {
                        Character operator = tempOperators.remove(i);
                        long num;

                        if (operator == '+')
                            num = tempNumbers.remove(i) + tempNumbers.remove(i);
                        else if (operator == '-')
                            num = tempNumbers.remove(i) - tempNumbers.remove(i);
                        else
                            num = tempNumbers.remove(i) * tempNumbers.remove(i);

                        tempNumbers.add(i, num);
                        break;
                    }
                }
            }

            answer = Math.max(Math.abs(tempNumbers.get(0)), answer);
            return;
        }

        for (int i = 0; i < ops.length; i++) {
            if (!check[i]) {
                check[i] = true;
                priorities[depth] = ops[i];
                dfs(depth + 1);
                check[i] = false;
            }
        }
    }

    private int getPriority(char to) {
        int priority;
        if (to == priorities[0]) priority = 2;
        else if (to == priorities[1]) priority = 1;
        else priority = 0;
        return priority;
    }
}