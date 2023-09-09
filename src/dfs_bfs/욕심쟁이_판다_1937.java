package dfs_bfs;

/**
 * 23. 09. 09 19시 00분 시작
 * 23. 09. 09 19시 30분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 주어진 조건
 * 1. 판다가 대나무를 이동하면서 현재 위치한 대나무 개수보다 더 많은 대나무가 있는 곳으로 이동하며 먹기 시작한다.
 * 2. 상, 하, 좌, 우로 이동할 수 있다.
 * 3. 조련사는 어디에 판다를 위치시켜야 최대로 많은 대나무를 먹으면서 이동할 수 있는지 구해야한다.
 *
 * 제한사항
 * 1 <= n <= 500
 * 대나무의 양 <= 1_000_000
 *
 * 풀이
 * DP와 DFS를 섞은 문제로, 이동하는 경로에 대해 미리 값을 구해놓고 비교하며 최대 값을 찾아가야한다.
 * 이전 풀이에서는 DP를 사용하지 않았는데, 시간초과가 발생하였다.
 */

public class 욕심쟁이_판다_1937 {
    private static int n, answer = 0;
    private static int[][] map;
    private static int[][] dp;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        dp = new int[n+1][n+1];

        // Step 1. 대나무 갯수 설정
        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Step 2. 이동하면서 최대값에 대한 갱신을 진행
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }

        System.out.println(answer);
    }

    public static int dfs(int x, int y) {
        // 이미 값이 있는 경우 최대 값 리턴
        if(dp[x][y] != 0) {
            return dp[x][y];
        }

        // 현재 위치 : 초기 값 1
        dp[x][y] = 1;

        for(int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if(nx > 0 && ny > 0 && nx <= n && ny <= n) {
                // 현재 위치보다 다음 위치가 대나무가 많은 경우만
                if(map[x][y] < map[nx][ny]) {
                    dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
                }
            }
        }
        return dp[x][y];
    }
}
