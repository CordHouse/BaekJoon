import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 동전_1 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // n 동전의 개수
        // k 동전의 가치
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n+1];
        int[] dp = new int[k+1];

        // Step 2. 동전을 담아준다.
        for(int i = 1; i < n+1; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1;
        // Step 3. 반복 -> 11111 11111 -> 와 같이 1로 만들수 있는 가지수
        for(int i = 1; i < n+1; i++){
            for(int j = coins[i]; j < k+1; j++){
                dp[j] += dp[j-coins[i]];
            }
            System.out.println(Arrays.toString(dp));
        }
        System.out.println(dp[k]);
    }
}
