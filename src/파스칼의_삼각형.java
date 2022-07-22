import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 파스칼의_삼각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] num = br.readLine().split(" ");
        int n = Integer.parseInt(num[0]);
        int k = Integer.parseInt(num[1]);

        int[][] dp = new int[n+1][n+1];
        dp[1][1] = 1;
        for(int i = 2; i<n+1; i++){
            dp[i][1] = 1;
            dp[i][i] = 1;
        }
        for(int i = 3; i<n+1; i++){
            for(int j = 2; j<dp[i].length; j++){
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }
        System.out.println(dp[n][k]);

    }
}
