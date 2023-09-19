package graph;

/**
 * 23. 09. 20 03시 33분 시작
 * 23. 09. 20 04시 20분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 주어진 조건
 * 1. 직사각형 모양의 그래프에서 (0, 0) 시작점, (n, m) 종료점으로 이동한다.
 * 2. 상하좌우로 이동가능하며 현재 위치보다 다음 위치는 작아야한다.
 * 3. 총 갈 수 있는 루트의 갯수는 몇가지인가?
 *
 * 제한사항
 * n, m <= 500
 * h <= 10_000
 *
 * 풀이
 * 가로와 세로가 500으로 주어질 수 있기 때문에 시간초과가 발생할 수 있다.
 * 따라서 중복계산을 방지하는 DP를 활용하여 문제를 해결해야한다.
 * 최종 경로에 도착하게 될 경우 1로 리턴하며 이전 값들에게 대해 더해주게 된다.
 * 다음 이동하는 경로에 대해 이미 계산된 값이 있다면 계산된 값을 현재 값에 누적해 더해준다.
 * 과정을 반복하면 최종 결과 값을 얻을 수 있다.
 * 즉, 계산하지 않는 부분만 계산해주면 DFS + DP 방식으로 문제를 해결 할 수 있게 된다.
 */

public class 내리막_길_1520 {
    private static int[][] map, dp;
    private static int n, m;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // Step 1. map, dp 초기화
        map = new int[n][m];
        dp = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; // -1로 초기화하는 이유는 중복계산을 방지할때 이동경로를 확인하기 위해서이다.
            }
        }

        // Step 2. DP 시작
        System.out.println(dfs(0, 0));
    }

    public static int dfs(int x, int y) {
        if(x == n-1 && y == m-1) {
            return 1;
        }
        if(dp[x][y] != -1) {
            return dp[x][y];
        }
        dp[x][y] = 0;

        // 상하좌우로 움직이면서 이미 계산된 값은 바로바로 누적시켜 중복계산을 없앤다.
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && ny >= 0 && nx < n && ny < m) {
                if(map[x][y] > map[nx][ny]) {
                    dp[x][y] += dfs(nx, ny);
                }
            }
        }
        return dp[x][y];
    }
}
