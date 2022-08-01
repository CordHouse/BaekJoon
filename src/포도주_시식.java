import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 포도주_시식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int loopCount = Integer.parseInt(br.readLine());
        int[] grape = new int[loopCount + 1];
        int[] dp = new int[loopCount + 1];

        for (int i = 1; i < loopCount + 1; i++) {
            grape[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = grape[1];
        if (loopCount >= 2)
            dp[2] = grape[1] + grape[2];

        for (int i = 3; i < loopCount + 1; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i-2], dp[i - 3] + grape[i - 1]) + grape[i]);
        }
        System.out.println(dp[loopCount]);
    }
}
