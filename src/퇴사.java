import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 퇴사 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int count = Integer.parseInt(br.readLine());
        int[] dp = new int[count + 1];
        int[][] consulting = new int[count + 1][2];
        int max = 0;

        // Step 2. 반복 -> 시간과 비용 담기
        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine());
            consulting[i][0] = Integer.parseInt(st.nextToken());
            consulting[i][1] = Integer.parseInt(st.nextToken());
        }

        // Step 3. 값 비교하기
        for (int i = 0; i <= count; i++) {
            // dp[i] : 현재 위치의 비용이기 때문에 가장 큰 값으로 바꿔줘야 한다.
            dp[i] = Math.max(dp[i], max);
            if(consulting[i][0] + i < count+1) {
//                System.out.println("앞 : " + dp[consulting[i][0] + i]);
//                System.out.println("뒤 : " + (dp[i] + consulting[i][1]));

                // dp[consulting[i][0] + i : 상담이 끝난 시점의 위치
                // dp[i] : 현재 위치의 비용
                // consulting[i][1] : 현재 위치의 상담 했을때 추가되는 비용
                // dp[i] + consulting[i][1] : 상담 후 더해지는 비용
                dp[consulting[i][0] + i] = Math.max(dp[consulting[i][0] + i], dp[i] + consulting[i][1]);
//                System.out.println(Arrays.toString(dp));
            }
            // max : dp[consulting[i][0] + i 로 인해 dp[i] 값이 변경되기 때문에 max 값을 다시 한 번 바꿔줘야 한다.
            max = Math.max(dp[i], max);
        }
    }
}
