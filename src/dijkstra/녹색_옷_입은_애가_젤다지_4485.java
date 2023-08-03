package dijkstra;

/**
 * 23. 08. 03 13시 28분 시작
 * 23. 08. 03 15시 21분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주어진 조건
 * 1. 상하좌우를 이동하면서 루피를 최소한으로 잃는 최소 금액을 구하시오.
 *
 * 제한사항
 * 2 <= N <= 125 (N=0 -> 종료 )
 * 0 <= k <= 9
 *
 * 풀이
 * 이번 문제는 BFS로 풀 수 있는 문제이다.
 * 우선순위 큐를 활용하여 누적한 합에 이동할 합의 값의 최소를 더해가는 방식으로 진행되야 최종적으로 가장 작은 값이
 * 누적값으로 표기된다.
 * 처음에 접근했던 방법으로는 방문 체크만 진행하고 4방향중 가장 작은 값만을 찾아갔었는데, 그렇게하면 문제의 의도와 다른
 * 값을 도출할 경우도 생기게 된다.
 * 그렇기 때문에 우선순위 큐를 사용해 가장 작은 합이 있는 위치로 돌아가 맵을 이동하다보면 최솟 값을 찾을 수 있게 되는 것이다.
 */

public class 녹색_옷_입은_애가_젤다지_4485 {
    private static int[][] map, cloneMap;
    private static boolean[][] visit;
    private static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 1;

        // 출력 값을 담기 위한 변수
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        do {
            map = new int[n][n]; // 입력 되는 맵 값
            cloneMap = new int[n][n]; // 이동하는 맵에 대한 계산되는 값을 저장
            for(int i = 0; i < n; i++) { // 클론이 되는 값은 모든 값이 가장 큰 정수 값이어야 한다.
                Arrays.fill(cloneMap[i], Integer.MAX_VALUE);
            }

            visit = new boolean[n][n];
            StringTokenizer st;
            // Step 1. 변수 입력받기
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // Step 2. BFS 시작
            bfs(0, 0);
            sb.append("Problem ").append(count).append(": ").append(cloneMap[n-1][n-1]).append("\n");
            n = Integer.parseInt(br.readLine());
            count++;
        } while(n != 0);
        System.out.println(sb);
    }

    public static void bfs(int x, int y) {
        // 우선순위 큐로 입력되는 큐 값중 가장 합이 작은 값을 도출한다.
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.sum - o2.sum);
        visit[0][0] = true;
        queue.add(new Node(x, y, map[0][0])); // 주의 합은 0이 아니라, 맵의 가장 첫번째 값으로 초기화한다.

        // Step 3. 반복하면서 최소 값을 찾아간다.
        while(!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + node.x;
                int ny = dy[i] + node.y;

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    // 이동하는 위치에 대해서 가장 작은 값을 찾아간다.
                    if (!visit[nx][ny] && cloneMap[nx][ny] > map[nx][ny] + node.sum) {
                        cloneMap[nx][ny] = map[nx][ny] + node.sum;
                        visit[nx][ny] = true;
                        queue.add(new Node(nx, ny, cloneMap[nx][ny]));
                    }
                }
            }
        }
    }

    static class Node {
        int x, y;
        int sum;
        public Node(int x, int y, int sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }
    }
}
