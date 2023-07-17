package graph;

/***
 * 23. 07. 17 01시 10분 시작
 * 23. 07. 17 02시 50분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주어진 조건
 * 1. 여러 섬으로 이루어진 지도가 주어진다.
 * 2. 각 나라끼리 다리를 만들어 연결하는 최소의 다리 길이를 구하여라
 *
 * 제한사항
 * N <= 100
 * 0 = 바다, 1 = 육지
 *
 * 풀이
 * 이번 문제에서 주어진 지도를 보고 가장 먼저 생각했던 방법은 각 섬마다 번호를 지정하여 달라지는 숫자마다의 길이를
 * 구하려고 했었다. 하지만, 그 이후 어떤식으로 접근해야하는지 감이 잘 오지 않아서 블로그를 참고했다.
 * 내가 접근한 부분까진 50프로 정도 정답이었고, 이후 BFS를 통해 다른 값을 가진 섬 사이의 바다의 개수를 구해
 * 풀이해야했다.
 * 밑 코드대로 동작하면, 0과 붙어있는 1에서 출발해야 다른 섬과의 거리를 측정할 수 있다.
 * 아래 코드 중 예외되는 구간을 잘 보자
 */

public class 다리_만들기_2146 {
    private static int n;
    private static int[][] map;
    private static boolean[][] visit;
    private static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
    private static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // Step 1. 입력 값 매핑
        StringTokenizer st;
        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Step 2. 지도에 표기된 섬을 1번부터 번호로 매기기
        int target = 1;
        visit = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visit[i][j] && map[i][j] != 0) {
                    ireland(i, j, target++);
                }
            }
        }

        // Step 3. 각 섬이 다른 번호라는 것을 이용해 BFS 시작
        // 주의 : 매번 visit을 초기화 해주어야한다.
        visit = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] > 0) {
                    visit[i][j] = true; // 시작된 위치를 방문했다고 가정
                    bridge(i, j, map[i][j]); // BFS 시작
                    visit = new boolean[n][n]; // visit 초기화
                }
            }
        }
        System.out.println(min);
    }

    public static void bridge(int x, int y, int num) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y, 0)); // 노드를 만들어 길이를 측정

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if(node.length >= min) { // 길이가 최소 값보다 크다면 제외, -> 작은 값을 구하는 것이기 때문
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                // 해당 조건문이 중요하다
                // nx, ny 가 지도 범위 안에 있는 값이 아니라면 제외
                // 방문했던 곳이라면 제외 (바다를 방문한 곳)
                // 같은 섬이라면 제외
                if((nx < 0 || ny < 0 || nx >= n || ny >= n) || visit[nx][ny] || map[nx][ny] == num) {
                    continue;
                }

                // 바다인 경우만 추가해주고 거리를 구한다.
                if(map[nx][ny] == 0) {
                    visit[nx][ny] = true;
                    queue.add(new Node(nx, ny, node.length+1));
                }
                else {
                    // 최종적으로 바다가 아닌 경우 섬을 만난 것이기 때문에
                    // 최소 길이를 구한다.
                    min = Math.min(min, node.length);
                }
            }
        }
    }

    // 섬을 방문하며, 번호를 매긴다. (같은 섬 끼리, DFS 구간)
    public static void ireland(int x, int y, int target) {
        visit[x][y] = true;
        map[x][y] = target;

        for(int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
                if(!visit[nx][ny] && map[nx][ny] != 0) {
                    ireland(nx, ny, target);
                }
            }
        }
    }

    static class Node {
        int x;
        int y;
        int length;

        public Node(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }
    }
}
