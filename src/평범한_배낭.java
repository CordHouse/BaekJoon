import java.util.Scanner;

public class 평범한_배낭 {

    public static void main(String[] args)   {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] W = new int[n+1];
        int[] V = new int[n+1];

        int[][] dp = new int[n+1][k+1];

        for(int i=1;i<=n;i++) {
            W[i] = sc.nextInt();
            V[i] = sc.nextInt();
        }


        for(int i=1;i<=n;i++) {
            for(int j=1;j<=k;j++) {
                dp[i][j] = dp[i-1][j];
                if(j - W[i]>=0){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W[i]]+V[i]); // 이전 무게와 현재 무게를 비교하여 더 큰 값을 업데이트 시키고, j-W[i]를 기준으로 다음 값부터 이전 값 0번 배열부터 가져와 비교하기
                }
            }
//            System.out.println(Arrays.toString(dp[i])); // 배열 안에 값 확인해보기
        }
        System.out.println(dp[n][k]);
    }

}