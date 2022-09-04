import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 동전_2 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // n 동전의 개수, k 가치 수
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // coins = 동전을 담는 변수
        int[] dp = new int[k+1];
        int[] coins = new int[n+1];

        // Step 2. 반복 -> coin 담기
        for(int i = 1; i < n + 1; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }

        // dp 배열에 일정 크기의 값을 다 넣어주기
        Arrays.fill(dp, 100001);
        // 대신 0은 초기 값으로 잡아야한다. -> 0 + 1이 되어야 하기 때문이다.
        dp[0] = 0;
        // Step 3. 반복 -> dp 에 값 넣기
        for(int i = 1; i < n + 1; i++){
            for(int j = coins[i]; j < k + 1; j++) {
                dp[j] = Math.min(dp[j], dp[j-coins[i]] + 1);
            }
        }
        // 값이 변하지 않았다면 -1 출력
        if(dp[k] == 100001){
            System.out.println(-1);
        }
        else
            System.out.println(dp[k]);
    }
}
