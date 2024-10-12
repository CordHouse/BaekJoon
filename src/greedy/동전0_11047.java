package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 동전0_11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        int[] coin = new int[n];

        for(int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[100_000_001];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for(int i = 0; i < coin.length; i++) {
            dp[coin[i]] = 1;
        }

        for(int i = 0; i < coin.length; i++) {
            for(int j = 1; j < k + 1; j++) {
                if(j > coin[i]) {
                    dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
                }
            }
        }
        System.out.println(dp[k]);
    }
}
