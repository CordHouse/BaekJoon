package dfs_bfs;

/**
 * 23. 08. 20 11시 30분 시작
 * 23. 08. 21 00시 02분 종료
 * 성공 유무 -> 성공
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 주어진 조건
 * 1. 육지는 "L", 바다는 "W"로 나타내는 지도가 주어진다.
 * 2. 육지가 가장 큰 곳 중 가장 먼곳으로 두 곳에 보물이 묻혀있고 두 보물간의 최단 거리를 구해야한다.
 * 3. 이동은 상하좌우로 이동가능하며 육지만 이동가능하다.
 * 4. 이동할때마다 1시간씩 걸리게 된다.
 *
 * 제한사항
 * L, W <= 50
 *
 * 풀이
 * 이번 문제의 핵심은 모든 경우의 수를 따져보면서 보물간의 위치를 구하고, 그 위치에 따라 최단 거리를 구해
 * 그 값이 가장 큰 것을 고르는게 핵심이다.
 * 방문에 대한 처리를 진행하며, 각각의 L마다 다르게 적용되어야 하므로 노드 클래스를 만들어 이동에 대한 경로를 추적할 수 있다.
 * 또한, 카운트를 진행하면서 거리를 측정하기 때문에 최단 시간을 찾을 수 있게 된다.
 * 마지막으로 이전에는 내림차순 정렬로 빠르게 찾아보려 하였으나, 아래와 같은 예제에서 제대로 값이 안나왔기 때문에
 * 꼭 오름차순 정렬로 풀어야 한다.
 * 5 5
 * LLLLL
 * LWWWL
 * LWWWL
 * LWWWL
 * LLLLL
 * output:14
 * answer:8
 */

public class 보물섬_2589 {
    private static String[][] map;
    private static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
    private static int r, c;
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        // Step 1. 지도 입력 받기 및 우선순위 큐에 입력하기
        map = new String[r][c];
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.distance - o2.distance);
        for(int i = 0; i < r; i++) {
            String[] line = br.readLine().split("");
            for(int j = 0; j < c; j++) {
                map[i][j] = line[j];
                // L의 위치 저장하기 -> 이동경로
                if(map[i][j].equals("L")) {
                    queue.add(new Node(i, j, 0, new boolean[r][c]));
                }
            }
        }

        // Step 2. BFS로 반복하면서 최단 이동경로 찾기
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int count = 0; // 상하좌우가 막힌다면 0이기 때문에 그때만 값을 출력해보기

            // 현재 자신의 위치 체크
            if(!node.visit[node.x][node.y]) {
                node.visit[node.x][node.y] = true;
            }

            for(int i = 0; i < 4; i++) {
                int nx = dx[i] + node.x;
                int ny = dy[i] + node.y;

                if(nx >= 0 && ny >= 0 && nx < r && ny < c) {
                    if(!node.visit[nx][ny] && map[nx][ny].equals("L")) {
                        node.visit[nx][ny] = true; // 이동경로 체크
                        queue.add(new Node(nx, ny, node.distance+1, node.visit));
                        count++;
                    }
                }
            }
            // 상하좌우가 막히면 마지막 보물의 위치이므로 리턴 값과 비교하여 큰 값으로 변경하기
            if(count == 0) {
                answer = Math.max(answer, node.distance);
            }
        }

        System.out.println(answer);
    }

    static class Node {
        int x, y;
        int distance;
        boolean[][] visit;

        public Node(int x, int y, int distance, boolean[][] visit) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.visit = visit;
        }
    }
}
