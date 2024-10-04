package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _1943 {

    static class Coin {
        int value;
        int quantity;

        public Coin(int value, int quantity) {
            this.value = value;
            this.quantity = quantity;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < 3; t++) {
            int n = Integer.parseInt(br.readLine());
            Coin[] coins = new Coin[n];
            boolean[] dp = new boolean[100001];

            int total = 0;

            for (int i = 0; i < n; i++) {
                String[] input = br.readLine().split(" ");
                int value = Integer.parseInt(input[0]);
                int quantity = Integer.parseInt(input[1]);

                coins[i] = new Coin(value, quantity);
                total += value * quantity;

                for (int j = value; j <= value * quantity; j += value) {
                    dp[j] = true;
                }
            }

            if (total % 2 != 0) {
                sb.append("0").append("\n");
                continue;
            }
            if (dp[total / 2]) {
                sb.append("1").append("\n");
                continue;
            }

            dp[0] = true;
            boolean flag = true;

            for (Coin coin : coins) {
                int value = coin.value;
                int quantity = coin.quantity;
                int half = total / 2;

                if (value > half) {
                    sb.append("0").append("\n");
                    flag = false;
                    break;
                }

                for (int i = half; i >= value; i--) {
                    int remain = i - value;
                    if (dp[remain]) {
                        for (int j = 1; j <= quantity; j++) {
                            int sum = remain + j * value;
                            if (sum > half) break;
                            dp[sum] = true;
                        }
                    }
                }
            }

            if (flag) sb.append(dp[total / 2] ? "1" : "0").append("\n");
        }

        System.out.println(sb);
    }
}