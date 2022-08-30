import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB거리 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int count = Integer.parseInt(br.readLine());
        int[][] dp = new int[count+1][4];
        boolean[][] check = new boolean[count+1][4]; // 이전 색과 같은지 아닌지 확인

        // Step 2. 반복 -> 값 집어넣기
        for(int i = 1; i < count + 1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < 4; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Step 3. 반복 -> 점화식
        for(int i = 2; i < count + 1; i++){
            if(!check[i-1][1]){
                dp[i][1] = Math.min(dp[i-1][2], dp[i-1][3]) + dp[i][1];
                check[i-1][1] = true;
            }
            if(!check[i-1][2]){
                dp[i][2] = Math.min(dp[i-1][1], dp[i-1][3]) + dp[i][2];
                check[i-1][2] = true;
            }
            if(!check[i-1][3]){
                dp[i][3] = Math.min(dp[i-1][1], dp[i-1][2]) + dp[i][3];
                check[i-1][3] = true;
            }
        }

        // Step 4. 값 도출
        int min = Integer.MAX_VALUE;

        for(int i = 1; i < 4; i++){
            min = Math.min(min, dp[count][i]);
        }
        System.out.println(min);
    }
}
