import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 정수_삼각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int loop = Integer.parseInt(br.readLine());

        int[][] dp = new int[loop+1][loop+1];
        for(int i = 1; i<loop+1; i++){
            String[] tmp = br.readLine().split(" ");
            for(int j = 1; j<i+1; j++){
                dp[i][j] = Integer.parseInt(tmp[j-1]);
            }
        }
        for(int i = loop; i > 0; i--){
            for(int j = 1; j < i; j++){
                dp[i-1][j] += Math.max(dp[i][j], dp[i][j + 1]);
            }
        }
        System.out.println(dp[1][1]);
    }
}
