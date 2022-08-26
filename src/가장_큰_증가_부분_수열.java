import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 가장_큰_증가_부분_수열 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int[] dp = new int[count];
        int[] A = new int[count];
        StringTokenizer st = new StringTokenizer(br.readLine());

        // Step 2. 반복
        for(int i = 0; i < count; i++){
            int tmp = Integer.parseInt(st.nextToken());
            dp[i] = tmp; // 기존 값과 비교하기 위해 초기값을 같이 담아준다.
            A[i] = tmp;
        }

        // Step 3. dp 적용
        for(int i = 1; i < count; i++){
            for(int j = 0; j < i; j++){
                if(A[j] < A[i]){
                    if(dp[j]+A[i] > dp[i]){
                        dp[i] = dp[j] + A[i];
                    }
                }
            }
        }

        Arrays.sort(dp);
        System.out.println(dp[count-1]);

    }
}
