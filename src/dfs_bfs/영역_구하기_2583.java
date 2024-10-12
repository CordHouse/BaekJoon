package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 영역_구하기_2583 {
    private static boolean[][] visit;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static int count, n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);

        visit = new boolean[n][m];
        for(int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int lx = Integer.parseInt(st.nextToken());
            int ly = Integer.parseInt(st.nextToken());
            int rx = Integer.parseInt(st.nextToken());
            int ry = Integer.parseInt(st.nextToken());

            boundary(lx, ly, rx, ry);
        }

        int region = 0;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!visit[i][j]) {
                    dfs(j, i);
                    region++;
                    list.add(count);
                    count = 0;
                }
            }
        }
        Collections.sort(list);
        System.out.println(region);

        for(int v : list) {
            System.out.print(v + " ");
        }
    }

    private static void dfs(int x, int y) {
        visit[y][x] = true;
        count++;

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && ny >= 0 && nx < m && ny < n) {
                if(!visit[ny][nx]) {
                    dfs(nx, ny);
                }
            }
        }
    }

    private static void boundary(int lx, int ly, int rx, int ry) {
        for(int i = ly; i < ry; i++) {
            for(int j = lx; j < rx; j++) {
                visit[i][j] = true;
            }
        }
    }
}
