package dfs_bfs;

/***
 * 23. 07. 19 23시 20분 시작
 * 23. 07. 20 00시 24분 종료
 * 성공 유무 -> 성공
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 주어진 조건
 * 1. x, y, z에 대한 맵이 주어진다.
 * 2. (0, 0, 0) 일 때 입력받는 반복이 종료된다.
 * 3. S 에서 시작해 E 로 탈출해야 한다.
 *
 * 제한사항
 * 1 <= L, R, C <= 30
 * 출력
 * 탈출 성공 시 : Escaped in x minute(s).
 * 탈출 실패 시 : Trapped!
 *
 * 풀이
 * 해당 문제는 가장 기본적인 BFS 문제에서 z 좌표가 추가된 문제라고 할 수 있다.
 * BFS 문제를 좌표로 접근할 때, x, y, z에 대한 배열 위치를 헷갈리지 않는 것이 중요하다.
 */

public class 상범_빌딩_6593 {
    private static int[] dx = {1, 0, -1, 0, 0, 0}, dy = {0, -1, 0, 1, 0, 0}, dz = {0, 0, 0, 0, 1, -1};
    private static String[][][] map;
    private static boolean[][][] visit;
    private static int sx, sy, sz;
    private static int L, R, C, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // Step 1. 반복된 입력 받기
        while(true) {
            answer = 0; // 걸리는 시간
            sx = 0; // 시작 x 좌표
            sy = 0; // 시작 y 좌표
            sz = 0; // 시작 z 좌표
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            // Step 2. 무한반복 탈출 조건
            if (exit()) {
                System.out.print(sb);
                return;
            }

            // Step 3. 맵 채우기
            map = new String[L][R][C];
            for(int l = 0; l < L; l++) {
                for(int r = 0; r < R; r++) {
                    String[] s = br.readLine().split("");
                    for(int c = 0; c < C; c++) {
                        if(s[c].equals("S")) {
                            sx = r;
                            sy = c;
                            sz = l;
                        }
                        map[l][r][c] = s[c];
                    }
                }
                br.readLine();
            }

            // Step 4. bfs 시작
            visit = new boolean[L][R][C];
            bfs();

            // Step 5. 출력
            if(answer != 0) {
                sb.append("Escaped in ").append(answer).append(" minute(s).\n");
                continue;
            }
            sb.append("Trapped!\n");
        }
    }

    public static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(sx, sy, sz, 0));
        visit[sz][sx][sy] = true;

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            // E를 만났을때 바로 탈출
            if(map[node.z][node.x][node.y].equals("E")) {
                answer = node.time;
                return;
            }

            for(int i = 0; i < 6; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                int nz = node.z + dz[i];

                // 방문하지 않고, # 이 아닌 범위 안의 값은 모두 큐에 담아준다.
                if(check(nx, ny, nz) && !visit[nz][nx][ny] && !map[nz][nx][ny].equals("#")) {
                    visit[nz][nx][ny] = true;
                    queue.add(new Node(nx, ny, nz, node.time+1));
                }
            }
        }
    }

    public static boolean check(int x, int y, int z) {
        return x >= 0 && y >= 0 && z >= 0 && x < R && y < C && z < L;
    }

    public static boolean exit() {
        return L == 0 && R == 0 && C == 0;
    }

    static class Node {
        int x, y, z, time;
        public Node(int x, int y, int z, int time) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
        }
    }
}
