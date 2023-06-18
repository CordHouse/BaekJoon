package graph;

/***
 * 23. 06. 18 14시 46분 시작
 * 23. 06. 18 16시 53분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주어진 조건
 * 1. 상어의 처음 크기는 2이다.
 * 2. 상어는 한 칸씩 이동하며, 자기 자신보다 큰 물고기가 있는 칸은 지나갈 수 없다.
 * 3. 아기상어는 자기보다 작은 물고기만 먹을 수 있다.
 * 4. 크기가 같은 물고기는 먹을 수 없지만, 지나갈 수는 있다.
 * 5. 아기상어의 크기는 크기만큼의 물고기를 먹을때 1만큼 증가한다.
 * 6. 먹을 물고기가 없다면 엄마 상어에게 도움을 요청한다. -> 종료
 * 7. 먹을 수 있는 물고기가 1마리보다 많다면 가장 가까운 물고기를 먹는다.
 * 8. 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기 그러한 물고기가
 * 여럿이라면 가장 왼쪽에 물고기를 먹는다.
 *
 * 제한사항
 * 5 <= N <= 20
 * 0 빈칸
 * 1, 2, 3, 4, 5, 6 칸에 있는 물고기의 크기
 * 9 아기 상어의 위치 (아기 상어는 한 마리만 존재)
 *
 * 풀이
 * 해당 문제는 BFS와 Queue를 활용하여 문제를 해결해보았다.
 * 고려해야할 조건들이 많아 시간이 상당히 많이 들어갔던 문제이고, 해결이 되지 않아 구글을 참고했다.
 * 해당 조건에서 어렵게 느껴졌던 부분은 8번 조건이다.
 * 또한 풀이를 보면 알겠지만 BFS 활용이기 때문에 상당히 난이도 있는 문제였다.
 * 풀이 순서는 다음과 같다.
 * 먹을 수 있는 물고기가 있다면 먹으러 간다. (단 최소의 거리만을 찾아 이동한다 -> 상어를 이동시키면서 먹을 수 있으면 먹는다.)
 * 해당 행위가 반복되기 때문에 BFS에서는 같은 값 2가 연속으로 나오더라도 먹은 위치를 제외하곤 계속 다시 돌아서 확인한다.
 * 마지막으로 먹이가 없다면 그게 최종 이동거리이다.
 *
 * 핵심 -> 어떻게 상어가 이동한 위치마다 최소 값을 찾아내는가?
 * => 시뮬레이션을 통해 먼저 상어를 이동시켜 최소 값을 알아내고 이후나온 가장 최소 값을 먹는다.
 */
public class 아기_상어_16236 {
    private static int n;
    private static int[][] map; // 입력 받은 원본 배열
    private static int[][] dist; // 여러 물고기가 있을 때 거리 체크할 배열
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int sharkSize = 2; // 상어 사이즈
    private static int eat = 0; // 상어가 물고기 먹은 횟수
    private static int answer = 0; // 상어가 이동한 횟수
    private static int sharkX = -1; // 상어의 X 좌표
    private static int sharkY = -1; // 상어의 Y 좌표
    private static int minX, minY, minDist; // while문 탈출 조건으로 쓸 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        // Step 1. 상어와 물고기의 위치 초기화
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(line[j]);

                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0; // 상어가 있었던 위치도 이 후로 탐색해야 하므로 0으로 초기화
                }
            }
        }

        // Step 2. 더 이상 먹이가 없을때 까지 반복하며 이동 거리를 누적한다.
        while (true) {
            dist = new int[n][n]; // 거리 알아낼 배열 && 자동으로 방문 처리 됨
            minX = Integer.MAX_VALUE; // 상어에서 가장 가까이 있는 물고기 X좌표
            minY = Integer.MAX_VALUE; // 상어에서 가장 가까이 있는 물고기 Y좌표
            minDist = Integer.MAX_VALUE; // 상어에서 가장 가까이 있는 물고기 거리

            bfs(sharkX, sharkY); // 먹을 수 있는 물고기중 가장 최소 거리로 이동

            // 먹을 수 있는 물고기의 위치로 이동했다면
            if (minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
                eat++; // 상어가 먹은 물고기 횟수
                map[minX][minY] = 0; // 물고기를 먹었으므로 해당 위치는 0으로 갱신
                sharkX = minX; // 먹은 물고기의 X좌표가 현재 상어의 X좌표
                sharkY = minY; // 먹은 물고기의 Y좌표가 현재 상어의 Y좌표
                answer += dist[minX][minY]; // 상어가 이동한 횟수는 해당 dist배열의 값을 더한 값이다

                // 물고기를 먹은 횟수가 상어의 현재 사이즈와 같다면
                if (eat == sharkSize) {
                    sharkSize++; // 상어의 사이즈 + 1
                    eat = 0; // 물고기 먹은 횟수 0으로 갱신
                }
            } else { // minX, minY가 초기값과 같다면 더 이상 먹을 물고기가 없다는 뜻이다.
                break;
            }
        }

        System.out.println(answer);
    }

    static void bfs(int x, int y) {
        Queue<Fish> queue = new LinkedList<>();
        queue.add(new Fish(x, y));

        while (!queue.isEmpty()) {
            Fish fish = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = fish.x + dx[i];
                int ny = fish.y + dy[i];
                // 범위 안 && 그 위치가 이동할 수 있는 위치 && 아직 방문한 적 없는 위치 라면?
                if (isArea(nx, ny) && isMove(nx, ny) && dist[nx][ny] == 0) {
                    // 이동 거리는 1씩 증가하기 때문에 이전 거리에 1증가
                    dist[nx][ny] = dist[fish.x][fish.y] + 1;

                    // 해당 위치에 물고기가 있고 그 물고기가 상어보다 크기가 작다면?
                    if (isEat(nx, ny)) {
                        // 탐색한 위치에 있는 물고기의 거리가 가장 가까운 지 알아봄
                        if (minDist > dist[nx][ny]) { // 더 가까운 물고기라면?
                            minDist = dist[nx][ny]; // 최소 거리 갱신
                            minX = nx; // 갱신
                            minY = ny; // 갱신
                        }
                        else if (minDist == dist[nx][ny]) { // 최소 거리인 물고기가 많다면?
                            // 더 위에 있는 물고기 선택
                            if (minX == nx) {
                                // 그 위에 있는 물고기의 수가 많다면 가장 왼쪽에 있는 물고기 선택
                                if (minY > ny) {
                                    minY = ny;
                                }
                            }
                            else if (minX > nx) { // 더 위에 있는 물고기가 한 마리 라면
                                minX = nx;
                                minY = ny;
                            }
                        }
                    }
                    // 상어의 위치가 계속 갱신 되면서 먹을 수 있는 위치로 이동한다.
                    queue.add(new Fish(nx, ny));
                }
            }

        }
    }

    // map 범위 안에 있는지?
    static boolean isArea(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    // 상어가 먹을 수 있는 물고기인지? -> 상어 사이즈보다 작아야 됨
    static boolean isEat(int x, int y) {
        return map[x][y] != 0 && map[x][y] < sharkSize;
    }

    // 상어가 해당 위치로 이동할 수 있는지? -> 해당 위치가 비어 있거나(0) 물고기가 있다면 상어보다 크기가 작아야 됨
    static boolean isMove(int x, int y) {
        return map[x][y] <= sharkSize;
    }

    static class Fish {
        int x, y;

        Fish(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}