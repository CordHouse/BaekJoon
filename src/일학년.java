import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 일학년 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        // long 타입인 이유는 버퍼 오버 플로우 때문
        long[][] dp = new long[count + 1][21];
        int[] grade = new int[count + 1];

        for(int i = 1; i < count + 1; i++){
            grade[i] = Integer.parseInt(st.nextToken());
        }

        // dp[1][8] -> 처음 위치에 1값을 넣어 트리를 만들어 내려가면서 나온 가지수를 체크한다.
        // dp[2][5] == 1, dp[2][11] == 1 이런식으로 한줄에 나오는 가지수를 다 체크한다.
        dp[1][grade[1]] = 1;
        for(int i = 2; i < count; i++){
            for(int j = 0; j < 21; j++){
                if(dp[i-1][j]!=0){
                    if(j+grade[i] <= 20){
                        dp[i][j+grade[i]] += dp[i-1][j];
                    }
                    if(j-grade[i] >= 0){
                        dp[i][j-grade[i]] += dp[i-1][j];
                    }
                }
            }
        }
        System.out.println(dp[count-1][grade[count]]);
    }
}
