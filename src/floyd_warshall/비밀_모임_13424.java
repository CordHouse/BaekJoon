package floyd_warshall;

/**
 * 23. 08. 09 13시 50분 시작
 * 23. 08. 09 14시 56분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 주어진 조건
 * 1. 정점의 개수와 간선의 개수가 주어지며, 학생들이 서있는 위치 정보가 주어진다.
 * 2. 양방향으로 연결되어 있는 비밀 통로를 이동할 수 있다.
 *
 * 제한사항
 * 2 <= N <= 100
 * N-1 <= M <= N(N - 1)/2
 * 1 <= K <= N
 *
 * 풀이
 * 플로이드 워셜을 통해 문제를 풀어볼 수 있다.
 * 플로이드 워셜은 모든 정점에 대한 거리 비용을 최소로하는 거점을 찾을 수 있다.
 * 정점에 대한 테이블 값을 만들고 지날 수 있는 z라는 거점을 더해야 하기 때문에 INF라는 값을 설정해야한다.
 * INF 값이 MAX_VALUE가 되어버리면 정수 최대값이 넘어 값이 이상하게 출력될 수 있기 때문에 두 값을 더했을 때 넘지
 * 않도록 설정해준다.
 * 마지막으로 출력시에는 어떤 노드를 선택하느냐에 따라 최소값이 달라질 수 있기 때문에 모든 경우의 수를 보기 위해
 * n번의 반복을 해야하고, 그 중 가장 작은 값을 선택해야 한다.
 * 중요한 점은 테이블을 만들때 모든 정점에 대한 최소 값으로 갱신되어 있기 때문에 현재 위치에서 이동할 위치만 잡으면
 * 값을 충분히 구할 수 있게 된다.
 */

public class 비밀_모임_13424 {
    private static int[][] map;
    private static final int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        // Step 1. 사이클 설정
        for(int cycle = 0; cycle < t; cycle++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // Step 2. 거리 값을 담을 테이블 초기화
            map = new int[n+1][n+1];
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    map[i][j] = iEqualJ(i, j);
                }
            }

            // Step 3. 간선 정보에 따른 정보 입력
            // 중복이 주어지지 않기 때문에 가능!
            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int distance = Integer.parseInt(st.nextToken());

                map[s][e] = distance;
                map[e][s] = distance;
            }

            int k = Integer.parseInt(br.readLine()); // 내가 고려할 인원 수
            // 현재 학생들이 서있는 위치
            int[] player = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            // Step 4. 최소 값으로 테이블 갱신
            // z : 중간 지점
            // i : 시작 지점
            // j : 끝 지점
            for(int z = 1; z <= n; z++) {
                for(int i = 1; i <= n; i++) {
                    for(int j = 1; j <= n; j++) {
                        if(i != j) {
                            map[i][j] = Math.min(map[i][j], map[i][z] + map[z][j]);
                        }
                    }
                }
            }

            // Step 5. 출력
            // 1 ~ n까지의 경우에 대하여 모든 학생들의 최소 값을 구한다.
            int index = -1;
            int min = INF;
            for(int i = 1; i<= n; i++) {
                int sum = 0;

                for(int person : player) {
                    sum += map[person][i];
                }

                if(sum < min) {
                    index = i;
                    min = sum;
                }
            }

            System.out.println(index);
        }

    }

    public static int iEqualJ(int i, int j) {
        if(i == j) {
            return 0;
        }
        return INF;
    }
}
