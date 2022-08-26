import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// M개에서 N개를 선택 -> 중복 x = 조합을 사용하자! ( nCr )
// 성질 두가지!
// 1번 : n+1Cr+1 = nCr + nCr+1
// 2번 : nC0 = nCn = 1
public class 다리_놓기 {
    static int[][] dp = new int[30][30];
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        StringTokenizer st;

        // Step 2. 반복
        for(int i = 0; i<count; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
//            int[][] dp = new int[n+1][r+1];

            System.out.println(combination(n, r));
        }
    }
    // Step 3. 함수 선언 Combination
    public static int combination(int n, int r){
        // 이미 들어갔던 곳이면 현재의 값을 리턴해준다.
        if(dp[n][r] > 0)
            return dp[n][r];
        // 2번째 성질 n == n(r), r == 0
        else if(n == r || r == 0)
            return dp[n][r] = 1;
        // 1번째 성질 n+1Cr+1 = nCr + nCr+1
        return dp[n][r] = combination(n-1, r-1) + combination(n-1, r);
    }
}
