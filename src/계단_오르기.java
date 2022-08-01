import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 계단_오르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int loopCount = Integer.parseInt(br.readLine());
        int[] stair = new int[loopCount + 1];
        int[] dp = new int[loopCount + 1];

        for (int i = 1; i < loopCount + 1; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = stair[1];
        if(loopCount>=2)
            dp[2] = stair[1] + stair[2];

        for (int i = 3; i < loopCount + 1; i++) {
            dp[i] = Math.max(dp[i-2], dp[i-3] + stair[i-1]) + stair[i];
        }
        System.out.println(dp[loopCount]);
    }
}
