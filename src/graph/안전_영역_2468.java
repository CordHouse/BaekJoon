package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 안전_영역_2468 {
    private static int[][] map;
    private static boolean[][] visit;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int max = 0;
        int answer = 1;

        // Step 1. map 테이블 값 넣어 만들기
        StringTokenizer st;
        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        // Step 2. 비가 오는건 2 ~ 100까지의 정수로 표현하여 계산
        // 계산하고 올때 최대 값을 여기서 갱신
        for(int k = 1; k <= max; k++) {
            visit = new boolean[n][n];
            int region = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(!visit[i][j] && map[i][j] > k) {
                        region++;
                        dfs(i, j, k);
                    }
                }
            }
            answer = Math.max(answer, region);
        }

        System.out.println(answer);
    }

    private static void dfs(int x, int y, int rain) {
        visit[x][y] = true;

        for(int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
                if(!visit[nx][ny] && map[nx][ny] > rain) {
                    dfs(nx, ny, rain);
                }
            }
        }
    }
}
