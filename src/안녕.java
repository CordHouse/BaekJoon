import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 안녕 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] L = new int[n+1];
        int[] J = new int[n+1];
        int[] dp = new int[101];

        // Step 2. 문자열 나누기
        StringTokenizer stL = new StringTokenizer(br.readLine());
        StringTokenizer stJ = new StringTokenizer(br.readLine());
        for(int i = 1; i < n+1; i++){
            L[i] = Integer.parseInt(stL.nextToken());
            J[i] = Integer.parseInt(stJ.nextToken());
        }

        // Step 3. 반복 -> 체력은 최대 100이기 때문에 100부터 시작
        for(int i = 1; i<n+1; i++){
            for(int j = 100; j > L[i]; j--) {
                if(j - L[i] < 100 || j - L[i] > 0) {
                    dp[j] = Math.max(dp[j], dp[j - L[i]] + J[i]);
                }else{
                    dp[j] = 0;
                }
            }
        }

        System.out.println(dp[100]);
    }
}
