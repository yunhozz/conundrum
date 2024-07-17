package programmers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 가장많이받은선물 {

    public int solution(String[] friends, String[] gifts) {
        int[][] board = new int[friends.length][friends.length];
        Arrays.stream(board)
                .forEach(b -> Arrays.fill(b, 0));
        List<String> fList = Arrays.stream(friends)
                .collect(Collectors.toList());

        for (int i = 0; i < gifts.length; i++) {
            String[] gift = gifts[i].split(" ");
            String giver = gift[0];
            String taker = gift[1];
            board[fList.indexOf(giver)][fList.indexOf(taker)]++;
        }

        int[] zisu = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            int g = 0, t = 0;
            for (int j = 0; j < friends.length; j++) {
                if (i == j) continue;
                g += board[i][j];
                t += board[j][i];
            }
            zisu[i] = g - t;
        }

        int[] result = new int[friends.length];
        Arrays.fill(result, 0);

        for (int i = 0; i < friends.length; i++) {
            for (int j = i; j < friends.length; j++) {
                if (i == j) continue;
                int a = board[i][j];
                int b = board[j][i];

                if (a == b) {
                    if (zisu[i] > zisu[j]) result[i]++;
                    if (zisu[i] < zisu[j]) result[j]++;
                } else {
                    if (a > b) result[i]++;
                    if (a < b) result[j]++;
                }
            }
        }

        int answer = Integer.MIN_VALUE;
        for (int n : result) {
            answer = Math.max(answer, n);
        }

        return answer;
    }
}
