import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 카드_구매하기 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dp = new int[count+1];
        int[] cost = new int[count+1];
        int answer = 0;

        // Step 2. 반복 -> 값 집어넣기
        for(int i = 1; i < count + 1; i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }

        // Step 3. 반복 -> dp 값 집어넣기
        // dp[i] : 현재 dp에 담긴 값
        // dp[i-j] + cost[j] : 각 dp[i-j] 위치에서 cost[j] 를 더한 값중 큰 값을 dp 값에 저장
        for(int i = 1; i < count + 1; i++){
            for(int j = 1; j < i + 1; j++) {
                dp[i] = Math.max(dp[i], dp[i-j] + cost[j]);
                answer = Math.max(dp[i], answer);
            }
        }

        System.out.println(answer);
    }
}
