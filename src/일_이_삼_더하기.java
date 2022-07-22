import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 일_이_삼_더하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int loop = Integer.parseInt(br.readLine());
        int[] num = new int[loop];
        for(int i = 0; i < loop; i++){
            num[i] = Integer.parseInt(br.readLine());
        }
        for(int j = 0; j < loop; j++) {
            int[] dp = new int[num[j] + 3];
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;
            for (int i = 4; i < num[j] + 1; i++) {
                dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
            }
            System.out.println(dp[num[j]]);
        }
    }
}
