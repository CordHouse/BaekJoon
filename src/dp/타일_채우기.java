package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 타일_채우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N % 2 == 1) {
            System.out.println(0);
            return ;
        }
        int[] dp = new int[Math.max(N/2, 2)];
        dp[0] = 3;
        dp[1] = 11;

        int exceptionSum = 0;
        for(int i = 2; i < N/2; i++) {
            exceptionSum += dp[i-2] * 2;
            dp[i] = dp[i-1] * 3 + 2 + exceptionSum; // 점화식
        }

        System.out.println(dp[N/2-1]);
    }
}
