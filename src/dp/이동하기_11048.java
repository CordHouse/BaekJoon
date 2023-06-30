package dp;

/***
 * 23. 06. 30 19시 11분 시작
 * 23. 06. 30 19시 25분 종료
 * 성공 유무 -> 성공
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 주어진 조건
 * 1. n * m의 크기로 이루어진 방에 사탕이 놓여있다.
 * 2. 이동 방향은 오른쪽, 대각선 아래, 아래 3가지 방향으로 이동 가능하다.
 * 3. 도착지는 dp[n][m]이고, 이때 사탕의 개수가 최대를 구하여라
 *
 * 제한사항
 * 1 <= N, M <= 1_000
 * (r, c) 사탕의 개수는 0 <= 사탕 <= 100
 *
 * 풀이
 * 전형적인 DP문제로, 이전의 사탕의 누적 값을 활용하여 다음 위치의 사탕의 총 개수를 파악한다.
 * 이동하면서 사탕의 개수를 최대로 해야하기 때문에 현재 위치에서 위, 왼쪽, 왼쪽 대각선 위 방향을 모두 살펴야한다.
 * 최종적으로 DP[n][m]에는 누적된 사탕의 개수가 최대값으로 담기게 된다.
 */

public class 이동하기_11048 {
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        dp = new int[n+1][m+1];

        for(int i = 1; i <= n; i++) {
            String[] line = br.readLine().split(" ");
            for(int j = 1; j <= m; j++) {
                dp[i][j] = Integer.parseInt(line[j-1]);
            }
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                dp[i][j] += Math.max(dp[i][j-1], Math.max(dp[i-1][j], dp[i-1][j-1]));
            }
        }
        System.out.println(dp[n][m]);
    }
}
