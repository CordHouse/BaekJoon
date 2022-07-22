import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 주의사항 : num으로 들어오는 숫자가 처음 나눠질때 2와 3둘다로 나눠질 수 있기 때문에 최소공배수인 경우도 생각해야 한다.
public class 일로_만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int[] dp = new int[num + 4];
        dp[2] = 1;
        dp[3] = 1;

        for(int i = 4; i < num+1; i++) {
            dp[i] = dp[i-1] + 1;
            if(i % 6 == 0){
                dp[i] = Math.min(dp[i/3]+1, dp[i/2]+1);
            }
            else if (i % 3 == 0) {
                dp[i] = Math.min(dp[i/3]+1, dp[i]);
            }
            else if(i % 2 == 0){
                dp[i] = Math.min(dp[i/2]+1, dp[i]);
            }
        }
        System.out.println(dp[num]);
    }
}
