package programmers;

import java.util.ArrayList;
import java.util.List;

public class 이모티콘할인행사 {
    private static final int[] discount = {10, 20, 30, 40};
    private static int maxOfSubscribe;
    private static int maxOfCost;

    private static class User {
        int percentage;
        int price;

        public User(int percentage, int price) {
            this.percentage = percentage;
            this.price = price;
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {
        List<User> userList = new ArrayList<>() {{
            for (int[] user : users) {
                add(new User(user[0], user[1]));
            }
        }};
        int emoLength = emoticons.length;
        int[] discounts = new int[emoLength];

        dfs(0, emoLength, discounts, userList, emoticons);

        return new int[] {maxOfSubscribe, maxOfCost};
    }

    private void dfs(int depth, int emoLength, int[] discounts, List<User> userList, int[] emoticons) {
        if (depth >= emoLength) {
            int subscribe = 0;
            int cost = 0;
            for (User user : userList) {
                int sum = 0;
                for (int i = 0; i < emoticons.length; i++) {
                    if (discounts[i] >= user.percentage) {
                        sum += emoticons[i] * (100 - discounts[i]) / 100;
                    }
                }
                if (sum >= user.price) {
                    subscribe++;
                } else {
                    cost += sum;
                }
            }

            if (subscribe > maxOfSubscribe) {
                maxOfSubscribe = subscribe;
                maxOfCost = cost;
            } else if (subscribe == maxOfSubscribe) {
                maxOfCost = Math.max(maxOfCost, cost);
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            discounts[depth] = discount[i];
            dfs(depth + 1, emoLength, discounts, userList, emoticons);
        }
    }
}
