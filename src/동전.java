import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동전 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());

        // Step 2. 세트 반복
        for (int loop = 1; loop < count + 1; loop++) {
            // Step 2-1. 변수 선언
            int coin = Integer.parseInt(br.readLine());
            int[] coins = new int[coin + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            // Step 2-2. 반복
            for (int i = 1; i < coin + 1; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int pay = Integer.parseInt(br.readLine());
            int[] dp = new int[pay+1];
            dp[0] = 1;

            // Step 2-3. 반복 -> DP 찾기
            for(int i = 1; i < coin+1; i++){
                for(int j = coins[i]; j < pay + 1; j++){
                    dp[j] += dp[j-coins[i]];
                }
            }
            System.out.println(dp[pay]);
        }
    }
}
