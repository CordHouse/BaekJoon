import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 카드_구매하기_2 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dp = new int[count + 1];
        int[] N = new int[count + 1];

        // Step 2. 반복 -> dp 값은 MAX_VALUE 로 선언 = 최솟 값을 찾기 위함
        for(int i = 1; i < count + 1; i++){
            N[i] = Integer.parseInt(st.nextToken());
            dp[i] = Integer.MAX_VALUE;
        }

        // Step 3. 반복 -> dp에 값 집어넣기
        for(int i = 1; i < count + 1; i++){
            for(int j = 1; j < i + 1; j++){
                dp[i] = Math.min(dp[i], dp[i-j] + N[j]);
            }
        }
        System.out.println(dp[count]);
    }
}
