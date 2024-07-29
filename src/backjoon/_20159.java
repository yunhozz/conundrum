package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _20159 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] evenSum = new int[N / 2];
        int[] oddSum = new int[N / 2];
        int z = 2;

        oddSum[0] = Integer.parseInt(st.nextToken());
        evenSum[0] = Integer.parseInt(st.nextToken());

        if (N == 2){
            System.out.print(Math.max(evenSum[0], oddSum[0]));
            return;
        }

        for (int i = 2; i < N; i++) {
            if (i % 2 == 0) {
                oddSum[i / 2] += oddSum[i - z] + Integer.parseInt(st.nextToken());
                z++;
            } else {
                evenSum[i / 2] += evenSum[i - z] + Integer.parseInt(st.nextToken());
            }
        }

        int answer = evenSum[N / 2 - 1];

        for (int i = 0; i < N / 2; i++) {
            int temp = 0;
            temp = oddSum[i] + (evenSum[N / 2 - 1] - evenSum[i]);
            if (temp > answer) {
                answer = temp;
            }
        }

        if (answer < oddSum[0] + evenSum[N / 2 - 2]) {
            answer = oddSum[0] + evenSum[N / 2 - 2];
        }

        for (int i = 0; i < N / 2 - 1; i++) {
            int temp = 0;
            temp = evenSum[N / 2 - 2] - evenSum[i] + oddSum[i + 1];
            if (temp > answer) {
                answer = temp;
            }
        }

        System.out.print(answer);
    }
}